package com.braiso_22.cozycave.feature_execution.data.local

import androidx.compose.runtime.mutableStateListOf
import com.braiso_22.cozycave.feature_execution.domain.Execution
import com.braiso_22.cozycave.feature_execution.domain.ExecutionRepository
import kotlinx.coroutines.flow.*

/**
 * Fake implementation of the [ExecutionRepository] to be used in tests.
 * It stores the [Execution]s in memory.
 */
class ExecutionRepositoryFake : ExecutionRepository {

    private val _executions = MutableStateFlow<List<Execution>>(listOf())
    private val executions = _executions.asStateFlow()

    override suspend fun addExecution(execution: Execution) {
        _executions.update { list ->
            list + execution
        }
    }

    override fun getExecutionsByRelatedId(id: Int): Flow<List<Execution>> {
        return executions.map { list ->
            list.filter { it.relatedId == id }
        }
    }

    override suspend fun getExecutionById(id: Int): Execution {
        TODO("Not yet implemented")
    }

    override suspend fun deleteExecution(execution: Execution) {
        TODO("Not yet implemented")
    }
}