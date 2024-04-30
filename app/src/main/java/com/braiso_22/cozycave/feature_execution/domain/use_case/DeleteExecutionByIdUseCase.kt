package com.braiso_22.cozycave.feature_execution.domain.use_case

import com.braiso_22.cozycave.feature_execution.domain.Execution
import com.braiso_22.cozycave.feature_execution.domain.ExecutionRepository

class DeleteExecutionByIdUseCase(private val repository: ExecutionRepository) {
    suspend fun invoke(id: Int) {
        val execution = Execution(id = id, relatedId = 0)
        repository.deleteExecutionById(execution)
    }
}