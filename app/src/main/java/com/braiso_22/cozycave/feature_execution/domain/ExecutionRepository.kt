package com.braiso_22.cozycave.feature_execution.domain

import kotlinx.coroutines.flow.Flow

interface ExecutionRepository {
    suspend fun addExecution(execution: Execution)
    fun getExecutionsByRelatedId(id: Int): Flow<List<Execution>>
}