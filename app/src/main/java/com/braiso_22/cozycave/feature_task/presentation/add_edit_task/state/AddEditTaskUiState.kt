package com.braiso_22.cozycave.feature_task.presentation.add_edit_task.state

import com.braiso_22.cozycave.feature_task.domain.Task

data class AddEditTaskUiState(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
)

fun Task.toUiState(): AddEditTaskUiState {
    return AddEditTaskUiState(
        id = id,
        name = name,
        description = description,
    )
}

fun AddEditTaskUiState.toTask(): Task {
    return Task(
        id = id,
        name = name,
        description = description,
    )
}