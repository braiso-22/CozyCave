package com.braiso_22.cozycave.feature_task.domain.use_case

import com.braiso_22.cozycave.feature_execution.domain.use_case.GetExecutionsByRelatedIdUseCase
import com.braiso_22.cozycave.feature_task.domain.TaskWithExecutions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTaskWithExecutionsUseCase(
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val getExecutionsByRelatedIdUseCase: GetExecutionsByRelatedIdUseCase,
) {
    suspend operator fun invoke(taskId: Int): Flow<TaskWithExecutions> {
        val task = getTaskByIdUseCase(taskId)
        return getExecutionsByRelatedIdUseCase(id = task.id).map {
            TaskWithExecutions(task, it)
        }
    }
}