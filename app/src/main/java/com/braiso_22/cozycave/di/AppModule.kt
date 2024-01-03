package com.braiso_22.cozycave.di

import android.app.Application
import androidx.room.Room
import com.braiso_22.cozycave.feature_task.data.TaskRepository
import com.braiso_22.cozycave.feature_task.data.TaskRepositoryImpl
import com.braiso_22.cozycave.feature_task.data.local.db.TaskDatabase
import com.braiso_22.cozycave.feature_task.domain.use_case.DeleteTasksUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.DeleteTaskUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.GetTaskByIdUseCase
import com.braiso_22.cozycave.feature_task.domain.use_case.GetTasksUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDatabase {
        return Room.databaseBuilder(
            app,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(
        taskDatabase: TaskDatabase,
    ): TaskRepository {
        return TaskRepositoryImpl(taskDatabase.taskDao)
    }

    @Provides
    @Singleton
    fun provideGetTasksUseCase(
        taskRepository: TaskRepository,
    ): GetTasksUseCase {
        return GetTasksUseCase(taskRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteTasksUseCase(
        taskRepository: TaskRepository,
    ): DeleteTaskUseCase {
        return DeleteTaskUseCase(taskRepository)
    }
}