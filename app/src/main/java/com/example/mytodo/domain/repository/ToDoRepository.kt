package com.example.mytodo.domain.repository

import com.example.mytodo.domain.model.ToDoEntity
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun getAllToDo() : Flow<List<ToDoEntity>>
    suspend fun getToDoById(id: Int) : ToDoEntity?
    suspend fun deleteToDo(toDoEntity: ToDoEntity)
    suspend fun deleteCompletedToDo()
    suspend fun insertToDo(toDoEntity: ToDoEntity)
}