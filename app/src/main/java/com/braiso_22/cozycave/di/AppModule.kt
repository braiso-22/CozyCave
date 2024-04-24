package com.braiso_22.cozycave.di

import android.app.Application
import androidx.room.Room
import com.braiso_22.cozycave.feature_execution.data.local.ExecutionRepositoryImpl
import com.braiso_22.cozycave.feature_execution.data.local.dao.ExecutionDao
import com.braiso_22.cozycave.feature_execution.domain.ExecutionRepository
import com.braiso_22.cozycave.feature_execution.domain.use_case.AddExecutionUseCase
import com.braiso_22.cozycave.feature_execution.domain.use_case.GetExecutionByIdUseCase
import com.braiso_22.cozycave.feature_execution.domain.use_case.GetExecutionsByRelatedIdUseCase
import com.braiso_22.cozycave.feature_task.data.TaskRepositoryImpl
import com.braiso_22.cozycave.feature_task.data.local.dao.TaskDao
import com.braiso_22.cozycave.feature_task.domain.TaskRepository
import com.braiso_22.cozycave.feature_task.domain.use_case.AddTaskUseCase
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
    fun provideTaskDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME,
        ).build()
    }

    @Provides
    fun provideTaskDao(database: AppDatabase): TaskDao = database.taskDao

    @Provides
    @Singleton
    fun provideTaskRepository(
        dao: TaskDao,
    ): TaskRepository {
        return TaskRepositoryImpl(dao)
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

    @Provides
    @Singleton
    fun provideAddTasksUseCase(
        taskRepository: TaskRepository,
    ): AddTaskUseCase {
        return AddTaskUseCase(taskRepository)
    }

    @Provides
    @Singleton
    fun provideGetTaskByIdUseCase(
        taskRepository: TaskRepository,
    ): GetTaskByIdUseCase {
        return GetTaskByIdUseCase(taskRepository)
    }

    @Provides
    fun provideExecutionDao(
        appDatabase: AppDatabase,
    ): ExecutionDao = appDatabase.executionDao

    @Provides
    @Singleton
    fun provideExecutionRepository(
        dao: ExecutionDao,
    ): ExecutionRepository = ExecutionRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideAddExecutionUseCase(
        repository: ExecutionRepository,
    ): AddExecutionUseCase = AddExecutionUseCase(repository)

    @Provides
    @Singleton
    fun provideGetExecutionsByRelatedIdUseCase(
        repository: ExecutionRepository,
    ): GetExecutionsByRelatedIdUseCase = GetExecutionsByRelatedIdUseCase(repository)

    @Provides
    @Singleton
    fun provideGetExecutionByIdUseCase(
        repository: ExecutionRepository,
    ): GetExecutionByIdUseCase = GetExecutionByIdUseCase(repository)


}