package com.braiso_22.cozycave.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.braiso_22.cozycave.feature_execution.data.local.dao.ExecutionDao
import com.braiso_22.cozycave.feature_execution.data.local.entities.LocalExecution
import com.braiso_22.cozycave.feature_task.data.local.dao.TaskDao
import com.braiso_22.cozycave.feature_task.data.local.entities.LocalTask

@Database(
    entities = [
        LocalTask::class,
        LocalExecution::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val executionDao: ExecutionDao

    companion object {
        const val DATABASE_NAME = "cozycave_db"
    }
}