package com.braiso_22.cozycave.feature_task.data

/**
 * External data layer representation of a Task.
 */
data class Task (
    val id: Int? = null,
    val name: String,
    val description: String? = null,
    val frequency: String,
    val initialDate: Long,
    val days: String? = null,
)