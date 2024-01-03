package com.braiso_22.cozycave.feature_task.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.braiso_22.cozycave.feature_task.domain.Task

/**
 * Entity for the [Task] table in room database.
 */
@Entity
data class LocalTask(
    @PrimaryKey val id: Int? = null,
    val name: String,
    val description: String? = null,
    val frequency: String,
    val initialDate: Long,
    val days: String? = null,
)

/**
 * Extension function to convert a [LocalTask] into a [Task].
 */
fun LocalTask.asTask(): Task {
    return Task(
        id = id,
        name = name,
        description = description,
        frequency = frequency,
        initialDate = initialDate,
        days = days,
    )
}

/**
 * Extension function to convert a [Task] into a [LocalTask].
 */
fun localTaskfromTask(task: Task): LocalTask {
    return LocalTask(
        id = task.id,
        name = task.name,
        description = task.description,
        frequency = task.frequency,
        initialDate = task.initialDate,
        days = task.days,
    )
}

