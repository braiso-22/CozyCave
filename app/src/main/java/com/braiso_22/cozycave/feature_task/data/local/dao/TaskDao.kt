package com.braiso_22.cozycave.feature_task.data.local.dao

import androidx.room.*
import com.braiso_22.cozycave.feature_task.data.local.entities.LocalTask
import kotlinx.coroutines.flow.Flow

/**
 * Dao for the [LocalTask] entity.
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM LocalTask")
    fun getTasks(): Flow<List<LocalTask>>

    @Query("SELECT * FROM LocalTask WHERE id = :id")
    suspend fun getTask(id: Int): LocalTask?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: LocalTask)

    @Delete
    suspend fun deleteTask(task: LocalTask)
}