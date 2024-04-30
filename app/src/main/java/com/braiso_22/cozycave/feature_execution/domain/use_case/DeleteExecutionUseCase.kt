package com.braiso_22.cozycave.feature_execution.domain.use_case

import com.braiso_22.cozycave.feature_execution.domain.Execution
import com.braiso_22.cozycave.feature_execution.domain.ExecutionRepository

class DeleteExecutionUseCase(private val repository: ExecutionRepository) {
    suspend operator fun invoke(execution: Execution) = repository.deleteExecution(execution)
}