package com.braiso_22.cozycave.feature_task.domain.use_case

import com.braiso_22.cozycave.feature_task.data.TaskRepository
import com.braiso_22.cozycave.feature_task.data.Task
/**
 * Use case for getting all the [Task]s.
 */
class GetTasksUseCase(
    private val taskRepository: TaskRepository
) {
    operator fun invoke() = taskRepository.getTasks()
}