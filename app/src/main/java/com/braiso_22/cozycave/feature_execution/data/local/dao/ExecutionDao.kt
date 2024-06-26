package com.braiso_22.cozycave.feature_execution.data.local.dao

import androidx.room.*
import com.braiso_22.cozycave.feature_execution.data.local.entities.LocalExecution
import com.braiso_22.cozycave.feature_execution.domain.Execution
import kotlinx.coroutines.flow.Flow

/**
 * Dao for the [LocalExecution] entity.
 */
@Dao
interface ExecutionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExecution(execution: LocalExecution)

    @Query("SELECT * FROM LocalExecution WHERE relatedId = :id")
    fun getExecutionsByRelatedId(id: Int): Flow<List<LocalExecution>>

    @Query("SELECT * FROM LocalExecution WHERE id = :id LIMIT 1")
    suspend fun getExecutionById(id: Int): LocalExecution?

    @Delete
    suspend fun deleteExecutionById(execution: LocalExecution)
}