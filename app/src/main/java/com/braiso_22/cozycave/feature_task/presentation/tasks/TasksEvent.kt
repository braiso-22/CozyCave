package com.braiso_22.cozycave.feature_task.presentation.tasks

sealed class TasksEvent {
    data class Delete(val id: Int) : TasksEvent()
    data object UndoDeletion : TasksEvent()
}