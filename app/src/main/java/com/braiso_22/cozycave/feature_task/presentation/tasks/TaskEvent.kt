package com.braiso_22.cozycave.feature_task.presentation.tasks

sealed class TaskEvent {
    data class Delete(val id: Int) : TaskEvent()
    data object UndoDeletion : TaskEvent()
}