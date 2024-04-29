package com.braiso_22.cozycave.feature_execution.presentation.add_edit_execution.state

import com.braiso_22.cozycave.feature_execution.domain.Execution
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class AddEditExecutionUiState(
    val id: Int = 0,
    val startDate: String = "",
    val startTime: String = "",
    val endDate: String = "",
    val endTime: String = "",
    val relatedId: Int = 0,
    val isEditExecution: Boolean = false,
    val isFinished: Boolean = false,
)

fun Execution.toUiState() = AddEditExecutionUiState(
    id = id,
    startDate = startDateTime.toFormatedDate(),
    startTime = startDateTime.toFormatedTime(),
    endDate = endDateTime.toFormatedDate(),
    endTime = endDateTime.toFormatedTime(),
    relatedId = relatedId,
    isFinished = finished,
)

fun LocalDateTime.toFormatedDate(): String {
    return this.format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    )
}

fun LocalDateTime.toFormatedTime(): String {
    return this.format(
        DateTimeFormatter.ofPattern("HH:mm")
    )
}

fun AddEditExecutionUiState.asExecution(): Execution {
    return Execution(
        id = id,
        startDateTime = LocalDateTime.of(
            LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            LocalTime.parse(startTime)
        ),
        endDateTime = LocalDateTime.of(
            LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            LocalTime.parse(endTime)
        ),
        relatedId = relatedId,
        finished = isFinished,
    )
}