package com.braiso_22.cozycave.feature_task.domain

import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task

    suspend fun insertTask(task: Task)

    suspend fun deleteTaskById(task: Task)
}