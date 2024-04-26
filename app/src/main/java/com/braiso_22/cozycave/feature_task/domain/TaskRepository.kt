package com.braiso_22.cozycave.feature_task.domain

import com.braiso_22.cozycave.feature_task.domain.Task
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface to be implemented in the data layer for accessing the [Task]s
 */
interface TaskRepository {
    /**
     * Returns a [Flow] of all the [Task]s.
     */
    fun getTasks(): Flow<List<Task>>
    /**
     * Returns a [Task] by its id.
     */
    suspend fun getTaskById(id: Int): Task
    /**
     * Inserts a [Task] into the database.
     */
    suspend fun insertTask(task: Task)
    /**
     * Deletes a [Task] from the database.
     */
    suspend fun deleteTask(task: Task)
}