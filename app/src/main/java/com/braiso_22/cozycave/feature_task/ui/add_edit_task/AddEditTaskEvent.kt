package com.braiso_22.cozycave.feature_task.ui.add_edit_task

import java.time.LocalDate
import java.time.LocalTime

sealed class AddEditTaskEvent {
    data class EnteredName(val name: String) : AddEditTaskEvent()
    data class EnteredDescription(val description: String) : AddEditTaskEvent()
    data class EnteredDays(val days: String) : AddEditTaskEvent()
    data class EnteredDate(val date: LocalDate?) : AddEditTaskEvent()
    data class EnteredTime(val time: LocalTime?) : AddEditTaskEvent()
    data object SaveTask : AddEditTaskEvent()
}