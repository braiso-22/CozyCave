package com.braiso_22.cozycave.feature_task.domain.use_case

import com.braiso_22.cozycave.feature_task.data.Task
import com.braiso_22.cozycave.feature_task.data.TaskRepository

/**
 * Use case for deleting a [Task].
 */
class DeleteTasksUseCase(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: Task) = taskRepository.deleteTask(task)
}