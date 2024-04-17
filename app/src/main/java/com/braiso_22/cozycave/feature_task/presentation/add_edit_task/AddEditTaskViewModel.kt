package com.braiso_22.cozycave.feature_task.presentation.add_edit_task


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braiso_22.cozycave.feature_task.domain.InvalidTaskException
import com.braiso_22.cozycave.feature_task.domain.Task
import com.braiso_22.cozycave.feature_task.domain.use_case.AddTaskUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.GetTaskByIdUseCase
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.AddEditTaskEvent.*
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.state.AddEditTaskUiState
import com.braiso_22.cozycave.feature_task.presentation.add_edit_task.state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(AddEditTaskUiState())
    val state: State<AddEditTaskUiState> = _state
    private var updateStateJob: Job? = null

    private var _eventFlow = MutableSharedFlow<UiEvent>()

    private var currentTaskId: Int? = null

    init {
        savedStateHandle.get<Int>("taskId").let { taskId ->
            if (taskId != null) {
                viewModelScope.launch {
                    getTaskByIdUseCase(taskId)?.also {
                        currentTaskId = it.id
                        _state.value = it.toUiState()
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
                    try {
                        addTaskUseCase(
                            Task(
                                name = state.value.name,
                                description = state.value.description,
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveTask)
                    } catch (e: InvalidTaskException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save the task."
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        data object SaveTask : UiEvent()
    }
}