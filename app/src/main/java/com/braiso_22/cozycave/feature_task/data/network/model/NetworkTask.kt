package com.braiso_22.cozycave.feature_task.data.network.model

import kotlinx.serialization.Serializable
import com.braiso_22.cozycave.feature_task.domain.Task
import com.braiso_22.cozycave.feature_task.data.local.entities.LocalTask

/**
 * Network representation of [Task]
 */
@Serializable
data class NetworkTask(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
)

/**
 * Extension function to convert a [NetworkTask] into a [Task].
 */
fun NetworkTask.asLocalTask(): LocalTask {
    return LocalTask(
        id = id,
        name = name,
        description = description,
    )
}