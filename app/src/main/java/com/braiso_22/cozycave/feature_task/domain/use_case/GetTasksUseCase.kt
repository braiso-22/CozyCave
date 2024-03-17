package com.braiso_22.cozycave.feature_task.domain.use_case

import com.braiso_22.cozycave.feature_task.domain.TaskRepository
import com.braiso_22.cozycave.feature_task.domain.Task
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting all the [Task]s.
 */
class GetTasksUseCase(
    private val taskRepository: TaskRepository,
) {
    /**
     * returns a [Flow] of all the [Task]s.
     */
    operator fun invoke(): Flow<List<Task>> = taskRepository.getTasks()
}