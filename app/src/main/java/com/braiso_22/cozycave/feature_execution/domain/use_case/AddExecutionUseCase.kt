package com.braiso_22.cozycave.feature_execution.domain.use_case

import com.braiso_22.cozycave.feature_execution.domain.Execution
import com.braiso_22.cozycave.feature_execution.domain.ExecutionRepository

/**
 * Use case to add an [Execution] to the data source.
 */
class AddExecutionUseCase(private val repository: ExecutionRepository) {
    suspend operator fun invoke(execution: Execution) {
        repository.addExecution(execution)
    }
}