package com.braiso_22.cozycave.feature_task.presentation.show_task_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.braiso_22.cozycave.feature_task.domain.use_case.GetTaskWithExecutionsUseCase
import com.braiso_22.cozycave.feature_task.presentation.show_task_detail.state.TaskDetailUiState
import com.braiso_22.cozycave.feature_task.presentation.show_task_detail.state.toUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TAG = "TaskDetailViewModel"

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val getTaskWithExecutionsUseCase: GetTaskWithExecutionsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(TaskDetailUiState())
    val state: State<TaskDetailUiState> = _state

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
}