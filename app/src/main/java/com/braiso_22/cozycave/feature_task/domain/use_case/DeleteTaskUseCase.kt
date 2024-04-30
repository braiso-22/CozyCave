package com.braiso_22.cozycave.feature_task.domain.use_case

import com.braiso_22.cozycave.feature_task.domain.Task
import com.braiso_22.cozycave.feature_task.domain.TaskRepository

class DeleteTaskUseCase(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(id: Int) {
        val task = Task(id = id)
        taskRepository.deleteTaskById(task)
    }
}