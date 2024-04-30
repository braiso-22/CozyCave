package com.braiso_22.cozycave.feature_task.presentation.tasks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braiso_22.cozycave.feature_task.domain.Task
import com.braiso_22.cozycave.feature_task.domain.use_case.AddTaskUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.DeleteTaskUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.GetTaskByIdUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.GetTasksUseCase
import com.braiso_22.cozycave.feature_task.presentation.tasks.TasksEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val deleteTasksUseCase: DeleteTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(TasksUiState())
    val state: State<TasksUiState> = _state

    private val _eventFlow = MutableSharedFlow<TaskUiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var lastDeletedTask: Task? = null

    private var updateStateJob: Job? = null

    init {
        updateState()
    }

    private fun updateState() {
        updateStateJob?.cancel()
        updateStateJob = getTasksUseCase().onEach { taskList ->
            _state.value = state.value.copy(
                tasks = taskList.map(Task::toUiState)
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: TasksEvent) {
        when (event) {
            is Delete -> {
                viewModelScope.launch {
                    val task = getTaskByIdUseCase(event.id)
                    lastDeletedTask = task
                    deleteTasksUseCase(task)
                    _eventFlow.emit(TaskUiEvent.ShowUndoDeletion)
                }
            }

            is UndoDeletion -> {
                viewModelScope.launch {
                    addTaskUseCase(lastDeletedTask ?: return@launch)
                    lastDeletedTask = null
                }
            }
        }
    }

    sealed class TaskUiEvent {
        data object ShowUndoDeletion : TaskUiEvent()
    }
}