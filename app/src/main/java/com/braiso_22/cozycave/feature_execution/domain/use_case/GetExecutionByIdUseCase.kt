package com.braiso_22.cozycave.feature_execution.domain.use_case

import com.braiso_22.cozycave.feature_execution.domain.ExecutionRepository


class GetExecutionByIdUseCase(
    private val executionRepository: ExecutionRepository,
) {
    suspend operator fun invoke(id: Int) = executionRepository.getExecutionById(id)
}