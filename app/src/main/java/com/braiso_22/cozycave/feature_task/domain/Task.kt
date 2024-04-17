package com.braiso_22.cozycave.feature_task.domain

/**
 * External domain layer representation of a Task.
 */
data class Task(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
)

/**
 * Exception thrown when a task is not valid.
 */
class InvalidTaskException(message: String) : Exception(message)
