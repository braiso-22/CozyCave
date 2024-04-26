package com.braiso_22.cozycave.feature_task.presentation.tasks

import com.braiso_22.cozycave.feature_task.domain.Task

data class TasksUiState(val tasks: List<TaskUiState> = emptyList())

data class TaskUiState(
    val id: Int = 0,
    val name: String,
    val description: String = "",
)

fun Task.toUiState(): TaskUiState {
    return TaskUiState(
        id = id,
        name = name,
        description = description
    )
}