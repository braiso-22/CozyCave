package com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braiso_22.cozycave.feature_execution.domain.use_case.GetExecutionsByRelatedIdUseCase
import com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.state.AddEditExecutionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "AddEditExecutionViewModel"

@HiltViewModel
class AddEditExecutionViewModel @Inject constructor(
    private val getExecutionByRelatedIdUseCase: GetExecutionsByRelatedIdUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(AddEditExecutionUiState())
    val state: State<AddEditExecutionUiState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        Log.i(TAG, "$TAG created")
        viewModelScope.launch {

        }
    }

    fun onEvent(changeState: AddEditExecutionEvent) {
        when (changeState) {
            is AddEditExecutionEvent.ChangeState -> {
                _state.value = changeState.state.copy(

                )
            }

            AddEditExecutionEvent.Save -> {
                Log.i(TAG, "Clicked on save button")
                viewModelScope.launch {

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

