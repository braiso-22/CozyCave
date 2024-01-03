package com.braiso_22.cozycave.feature_task.domain.use_case;

import com.braiso_22.cozycave.feature_task.domain.Task;
import com.braiso_22.cozycave.feature_task.domain.TaskRepository;
import com.braiso_22.cozycave.feature_task.domain.InvalidTaskException

/**
 * Use case to add a new [Task].
 */
class AddTaskUseCase(
    private val taskRepository: TaskRepository
) {
    @Throws(InvalidTaskException::class)
    suspend operator fun invoke(task: Task) {
        if(task.name.isBlank()) {
            throw InvalidTaskException("The name of the task can't be empty.")
        }
        taskRepository.insertTask(task)
    }
}

