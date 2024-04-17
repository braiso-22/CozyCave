package com.braiso_22.cozycave.feature_execution.domain.use_case

import com.braiso_22.cozycave.feature_execution.domain.ExecutionRepository

class GetExecutionsByRelatedIdUseCase(private val repository: ExecutionRepository) {
    operator fun invoke(id: Int) = repository.getExecutionsByRelatedId(id)
}