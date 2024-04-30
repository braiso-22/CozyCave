package com.braiso_22.cozycave.feature_execution.domain

import kotlinx.coroutines.flow.Flow

/**
 * Repository for [Execution]s.
 * It is used to abstract the data source from the domain layer.
 */
interface ExecutionRepository {
    suspend fun addExecution(execution: Execution)

    fun getExecutionsByRelatedId(id: Int): Flow<List<Execution>>

    suspend fun getExecutionById(id: Int): Execution

    suspend fun deleteExecutionById(execution: Execution)
}