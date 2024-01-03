package com.braiso_22.cozycave.feature_task.ui.add_edit_task

import java.time.LocalDate
import java.time.LocalTime

data class AddEditTaskState(
    val name: String = "",
    val description: String = "",
    val days: String = "",
    val date: LocalDate? = null,
    val time: LocalTime? = null,
)
