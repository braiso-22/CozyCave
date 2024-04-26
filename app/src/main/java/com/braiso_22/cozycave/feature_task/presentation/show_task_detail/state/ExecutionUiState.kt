package com.braiso_22.cozycave.feature_task.presentation.show_task_detail.state

import com.braiso_22.cozycave.feature_execution.domain.Execution
import com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.state.toFormatedDate
import com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.state.toFormatedTime

data class ExecutionUiState(
    val startDate: String,
    val endDate: String,
    val startHour: String,
    val endHour: String,
)

fun Execution.toUiState() = ExecutionUiState(
    startDate = this.startDateTime.toFormatedDate(),
    startHour = this.startDateTime.toFormatedTime(),
    endDate = this.endDateTime.toFormatedDate(),
    endHour = this.endDateTime.toFormatedTime(),
)