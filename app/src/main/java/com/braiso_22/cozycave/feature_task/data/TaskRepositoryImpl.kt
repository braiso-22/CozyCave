package com.braiso_22.cozycave.feature_task.data

import com.braiso_22.cozycave.feature_task.data.local.dao.TaskDao
import com.braiso_22.cozycave.feature_task.data.local.entities.LocalTask
import com.braiso_22.cozycave.feature_task.data.local.entities.asTask
import com.braiso_22.cozycave.feature_task.data.local.entities.toLocal
import com.braiso_22.cozycave.feature_task.domain.Task
import com.braiso_22.cozycave.feature_task.domain.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * [Task]s Repository implementation using the offline first approach.
 * @param taskDao [TaskDao] to access the Local database.
 */
class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
    /**
     * Returns a [Flow] of all the [Task]s. Using the offline first approach.
     */
    override fun getTasks(): Flow<List<Task>> {
        return taskDao.getTasks().map {
            it.map(LocalTask::asTask)
        }
    }

    override suspend fun getTaskById(id: Int): Task {
        val task = taskDao.getTask(id)
        return task?.asTask() ?: Task()
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task.toLocal())
    }

    override suspend fun deleteTaskById(task: Task) {
        taskDao.deleteTask(task.toLocal())
    }
}