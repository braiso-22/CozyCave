package com.braiso_22.cozycave.feature_execution.domain

import kotlinx.coroutines.flow.Flow

/**
 * Repository for [Execution]s.
 * It is used to abstract the data source from the domain layer.
 */
interface ExecutionRepository {
    /**
     * Adds an [Execution] to the data source.
     */
    suspend fun addExecution(execution: Execution)

    /**
     * Returns a [Flow] of all the [Execution]s related to a specific id.
     */
    fun getExecutionsByRelatedId(id: Int): Flow<List<Execution>>

    /**
     * Returns an [Execution] by its id.
     */
    suspend fun getExecutionById(id: Int): Execution
}