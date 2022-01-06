package com.example.mytodo.di

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.mytodo.data.local.ToDoDatabase
import com.example.mytodo.data.repository.ToDoRepositoryImpl
import com.example.mytodo.domain.repository.ToDoRepository
import com.example.mytodo.domain.use_cases.to_do_use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideToDoDatabase(app: Application) : ToDoDatabase =
        Room.databaseBuilder(
            app,
           ToDoDatabase::class.java,
            ToDoDatabase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideToDoRepository(toDoDatabase: ToDoDatabase) : ToDoRepository = ToDoRepositoryImpl(toDoDatabase.todoDao)

    @Provides
    @Singleton
    fun provideToDoUseCase(repository: ToDoRepository) : ToDoUseCases = ToDoUseCases(
        insertToDo = InsertToDo(repository = repository),
        deleteToDo = DeleteToDo(repository = repository),
        getAllToDo = GetAllToDo(repository = repository),
        getToDoById = GetToDoById(repository = repository),
        deleteCompletedToDo = DeleteCompletedToDo(repository = repository),
        getToDoByDeadLine = GetToDoByDeadLine(repository = repository)
    )

}