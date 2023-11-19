package com.braiso_22.cozycave.feature_task.ui.tasks

data class TasksUiState(val tasks: List<TaskUiState> = emptyList())

data class TaskUiState(
    val name: String,
    val description: String? = null,
    val days: String? = null,
)