package com.braiso_22.cozycave.feature_task.presentation.add_edit_task


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braiso_22.cozycave.feature_task.domain.Task
import com.braiso_22.cozycave.feature_task.domain.use_case.AddTaskUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.GetTaskByIdUseCase
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.AddEditTaskEvent.*
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.state.AddEditTaskUiState
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.state.toTask
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = mutableStateOf(AddEditTaskUiState())
    val state: State<AddEditTaskUiState> = _state

    private var _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentTaskId: Int? = null

    init {
        savedStateHandle.get<Int>("taskId").let { taskId ->
            viewModelScope.launch {
                val task = getTaskByIdUseCase(taskId ?: return@launch)
                currentTaskId = task.id
                _state.value = task.toUiState()

                savedStateHandle.get<Boolean>("edit").let { isEdit ->
                    if (isEdit != null) {
                        _state.value = _state.value.copy(isEditTask = isEdit)
                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditTaskEvent) {
        when (event) {
            is EnteredName -> {
                _state.value = state.value.copy(name = event.name)
            }

            is EnteredDescription -> {
                _state.value = state.value.copy(description = event.description)
            }

            is SaveTask -> {
                viewModelScope.launch {
                    addTaskUseCase(
                        state.value.toTask()
                    )
                    _eventFlow.emit(UiEvent.GoBack)
                }
            }

            is OnBack -> {
                viewModelScope.launch {
                    _eventFlow.emit(UiEvent.GoBack)
                }
            }

        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object GoBack : UiEvent()
    }
}