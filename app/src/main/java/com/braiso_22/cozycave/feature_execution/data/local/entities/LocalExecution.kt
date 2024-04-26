package com.braiso_22.cozycave.feature_execution.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.braiso_22.cozycave.feature_execution.domain.Execution
import java.time.LocalDateTime

/**
 * Entity for the [Execution] table in room database.
 */
@Entity
data class LocalExecution(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val startDateTime: String,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val endDateTime: String,
    val relatedId: Int,
    val finished: Boolean,
)

fun LocalExecution.asExecution(): Execution {
    return Execution(
        id = id,
        startDateTime = LocalDateTime.parse(startDateTime),
        endDateTime = LocalDateTime.parse(endDateTime),
        relatedId = relatedId,
        finished = finished
    )
}

fun Execution.toLocal(): LocalExecution {
    return LocalExecution(
        id = id,
        startDateTime = startDateTime.toString(),
        endDateTime = endDateTime.toString(),
        relatedId = relatedId,
        finished = finished,
    )
}