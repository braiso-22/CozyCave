package com.braiso_22.cozycave.feature_task.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.braiso_22.cozycave.feature_task.data.local.dao.TaskDao
import com.braiso_22.cozycave.feature_task.data.local.entities.LocalTask

/**
 * Database for the Task entity.
 */
@Database(
    entities = [LocalTask::class],
    version = 1,
    exportSchema = false,
)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "task_db"
    }
}