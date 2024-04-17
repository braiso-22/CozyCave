package com.braiso_22.cozycave.feature_task.presentation.add_edit_task


sealed class AddEditTaskEvent {
    data class EnteredName(val name: String) : AddEditTaskEvent()
    data class EnteredDescription(val description: String) : AddEditTaskEvent()
    data object SaveTask : AddEditTaskEvent()
}