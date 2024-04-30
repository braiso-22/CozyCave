package com.braiso_22.cozycave.feature_execution.data.local

import com.braiso_22.cozycave.feature_execution.data.local.dao.ExecutionDao
import com.braiso_22.cozycave.feature_execution.data.local.entities.LocalExecution
import com.braiso_22.cozycave.feature_execution.data.local.entities.asExecution
import com.braiso_22.cozycave.feature_execution.data.local.entities.toLocal
import com.braiso_22.cozycave.feature_execution.domain.Execution
import com.braiso_22.cozycave.feature_execution.domain.ExecutionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

/**
 * [Execution]s Repository implementation using the offline first approach.
 * @param dao [ExecutionDao] to access the Local database.
 */
class ExecutionRepositoryImpl(private val dao: ExecutionDao) : ExecutionRepository {
    override suspend fun addExecution(execution: Execution) {
        withContext(Dispatchers.IO) {
            dao.addExecution(execution.toLocal())
        }
    }

    override fun getExecutionsByRelatedId(id: Int): Flow<List<Execution>> {
        return dao.getExecutionsByRelatedId(id).map {
            it.map(LocalExecution::asExecution)
        }
    }

    override suspend fun getExecutionById(id: Int): Execution {
        val localExecution: LocalExecution? = dao.getExecutionById(id)
        return localExecution?.asExecution() ?: Execution(
            startDateTime = LocalDateTime.now(),
            endDateTime = LocalDateTime.now(),
            relatedId = 0
        )
    }

    override suspend fun deleteExecutionById(execution: Execution) {

    }
}