package com.braiso_22.cozycave.feature_task.presentation.show_task_detail.state

import com.braiso_22.cozycave.feature_execution.domain.Execution
import com.braiso_22.cozycave.feature_task.domain.TaskWithExecutions

data class TaskDetailUiState(
    val name: String = "Task",
    val completedExecutions: List<ExecutionUiState> = emptyList(),
    val unFinishedExecutions: List<ExecutionUiState> = emptyList(),
)

fun TaskWithExecutions.toUiState(): TaskDetailUiState = TaskDetailUiState(
    name = this.task.name,
    completedExecutions = this.executions.filter {
        it.finished
    }.map(Execution::toUiState),
    unFinishedExecutions = this.executions.filter {
        !it.finished
    }.map(Execution::toUiState)
)
