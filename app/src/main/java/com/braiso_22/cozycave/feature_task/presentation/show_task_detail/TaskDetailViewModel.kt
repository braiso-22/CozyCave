package com.braiso_22.cozycave.feature_task.presentation.show_task_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braiso_22.cozycave.feature_execution.domain.Execution
import com.braiso_22.cozycave.feature_execution.domain.use_case.AddExecutionUseCase
import com.braiso_22.cozycave.feature_execution.domain.use_case.DeleteExecutionUseCase
import com.braiso_22.cozycave.feature_execution.domain.use_case.GetExecutionByIdUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.GetTaskWithExecutionsUseCase
import com.braiso_22.cozycave.feature_task.presentation.show_task_detail.state.TaskDetailUiState
import com.braiso_22.cozycave.feature_task.presentation.show_task_detail.state.toUiState
import com.braiso_22.cozycave.feature_task.presentation.tasks.TasksViewModel.TaskUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "TaskDetailViewModel"

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val getTaskWithExecutionsUseCase: GetTaskWithExecutionsUseCase,
    private val deleteExecutionUseCase: DeleteExecutionUseCase,
    private val addExecutionUseCase: AddExecutionUseCase,
    private val getExecutionByIdUseCase: GetExecutionByIdUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(TaskDetailUiState(taskId = 0))
    val state: State<TaskDetailUiState> = _state

    private val _eventFlow = MutableSharedFlow<ExecutionUiEvent>()

    val eventFlow = _eventFlow.asSharedFlow()

    private var lastDeletedExecution: Execution? = null

    private val _taskId = mutableIntStateOf(0)

    init {
        Log.i(TAG, "$TAG created")
        savedStateHandle.get<Int>("taskId").let { taskId ->
            _taskId.intValue = taskId ?: return@let
        }
        viewModelScope.launch {
            getTaskWithExecutionsUseCase(_taskId.intValue).collectLatest { detail ->
                _state.value = detail.toUiState()
                Log.i(TAG, "TaskDetail data loaded")
            }
        }
    }

    fun onDeleteExecution(id: Int) {
        viewModelScope.launch {
            val execution = getExecutionByIdUseCase(id)
            lastDeletedExecution = execution
            deleteExecutionUseCase(execution)
            _eventFlow.emit(ExecutionUiEvent.ShowUiMessage)
        }
    }

    fun onCancelDeletion() {
        viewModelScope.launch {
            addExecutionUseCase(lastDeletedExecution ?: return@launch)
            lastDeletedExecution = null
        }
    }

    sealed class ExecutionUiEvent {
        data object ShowUiMessage : ExecutionUiEvent()
    }
}