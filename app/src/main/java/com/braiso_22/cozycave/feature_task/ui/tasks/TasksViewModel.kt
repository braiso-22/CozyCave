package com.braiso_22.cozycave.feature_task.ui.tasks

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braiso_22.cozycave.feature_task.domain.use_case.DeleteTasksUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.GetTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    deleteTasksUseCase: DeleteTasksUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(TasksUiState())
    val state: State<TasksUiState> = _state
    private var updateStateJob: Job? = null

    init {
        updateState()
    }

    private fun updateState() {
        updateStateJob?.cancel()
        updateStateJob = getTasksUseCase().onEach { tasks ->
            _state.value = state.value.copy(
                tasks = tasks.map {
                    TaskUiState(
                        name = it.name,
                        description = it.description,
                        days = it.days,
                    )
                }
            )
        }.launchIn(viewModelScope)
    }
}