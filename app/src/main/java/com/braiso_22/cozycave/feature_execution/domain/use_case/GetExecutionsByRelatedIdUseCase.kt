package com.braiso_22.cozycave.feature_execution.domain.use_case

import com.braiso_22.cozycave.feature_execution.domain.ExecutionRepository
import com.braiso_22.cozycave.feature_execution.domain.Execution

/**
 * Use case to get all the [Execution]s related to a specific id.

 */
class GetExecutionsByRelatedIdUseCase(private val repository: ExecutionRepository) {
    operator fun invoke(id: Int) = repository.getExecutionsByRelatedId(id)
}