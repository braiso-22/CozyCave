package com.braiso_22.cozycave.feature_task.domain.use_case

import com.braiso_22.cozycave.feature_task.domain.Task
import com.braiso_22.cozycave.feature_task.domain.TaskRepository

/**
 * Use case for getting a [Task] by its id.
 */
class GetTaskByIdUseCase(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(id: Int) = taskRepository.getTaskById(id)
}