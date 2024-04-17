package com.braiso_22.cozycave.feature_task.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.braiso_22.cozycave.feature_task.domain.Task

/**
 * Entity for the [Task] table in room database.
 */
@Entity
data class LocalTask(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String
)

/**
 * Extension function to convert a [LocalTask] into a [Task].
 */
fun LocalTask.asTask(): Task {
    return Task(
        id = id,
        name = name,
        description = description,
    )
}

/**
 * Extension function to convert a [Task] into a [LocalTask].
 */
fun Task.toLocal(): LocalTask {
    return LocalTask(
        id = id,
        name = name,
        description = description,
    )
}

