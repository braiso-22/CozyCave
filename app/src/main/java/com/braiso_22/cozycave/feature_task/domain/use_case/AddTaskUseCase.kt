package com.braiso_22.cozycave.feature_task.domain.use_case;

import com.braiso_22.cozycave.feature_task.domain.Task;
import com.braiso_22.cozycave.feature_task.domain.TaskRepository;
import com.braiso_22.cozycave.feature_task.domain.InvalidTaskException

/**
 * Use case to add a new [Task].
 */
class AddTaskUseCase(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(task: Task) = taskRepository.insertTask(task)
}

