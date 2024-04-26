package com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braiso_22.cozycave.R
import com.braiso_22.cozycave.feature_execution.domain.use_case.AddExecutionUseCase
import com.braiso_22.cozycave.feature_execution.domain.use_case.GetExecutionByIdUseCase
import com.braiso_22.cozycave.feature_execution.domain.use_case.GetExecutionsByRelatedIdUseCase
import com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.state.AddEditExecutionUiState
import com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.state.asExecution
import com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.state.toUiState
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "AddEditExecutionViewModel"

@HiltViewModel
class AddEditExecutionViewModel @Inject constructor(
    private val getExecutionByIdUseCase: GetExecutionByIdUseCase,
    private val addExecutionUseCase: AddExecutionUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(AddEditExecutionUiState())
    val state: State<AddEditExecutionUiState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        Log.i(TAG, "$TAG created")
        savedStateHandle.get<Int>("executionId").let { executionId ->
            viewModelScope.launch {
                val execution = getExecutionByIdUseCase(
                    executionId ?: return@launch
                )
                _state.value = execution.toUiState()
                savedStateHandle.get<Boolean>("edit").let { isEdit ->
                    _state.value = _state.value.copy(
                        isEditExecution = isEdit ?: return@launch
                    )
                }
                savedStateHandle.get<Int>("relatedId").let { id ->
                    _state.value = _state.value.copy(relatedId = id ?: return@launch)
                }

            }
        }
    }

    fun onEvent(changeState: AddEditExecutionEvent) {
        when (changeState) {
            is AddEditExecutionEvent.ChangeState -> {
                _state.value = changeState.state
            }

            AddEditExecutionEvent.Save -> {
                Log.i(TAG, "Clicked on save button")
                if (!state.value.isFinished) {
                    _state.value = _state.value.copy(
                        endTime = _state.value.startTime,
                        endDate = _state.value.startDate
                    )
                }
                viewModelScope.launch {
                    addExecutionUseCase(
                        _state.value.asExecution()
                    )
                    _eventFlow.emit(UiEvent.SavedCloseScreen(R.string.execution_saved_correctly))
                }
            }

            AddEditExecutionEvent.Cancel -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.CloseScreen)
                }
                Log.i(TAG, "AddEditExecution creation cancelled")
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: Int) : UiEvent()
        data object CloseScreen : UiEvent()
        data class SavedCloseScreen(val message: Int) : UiEvent()
    }
}

