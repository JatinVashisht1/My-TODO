package com.example.mytodo.data.repository

import com.example.mytodo.data.local.ToDoDao
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow

class ToDoRepositoryImpl(private val toDoDao: ToDoDao) : ToDoRepository {
    override fun getAllToDo(): Flow<List<ToDoEntity>> {
        return toDoDao.getAllToDo()
    }

    override suspend fun deleteCompletedToDo() {
        return toDoDao.deleteCompletedToDo()
    }

    override suspend fun getToDoById(id: Int): ToDoEntity? {
        return toDoDao.getToDoById(id = id)
    }

    override suspend fun deleteToDo(toDoEntity: ToDoEntity) {
        toDoDao.deleteToDo(toDoEntity = toDoEntity)
    }

    override suspend fun insertToDo(toDoEntity: ToDoEntity) {
        toDoDao.insertToDo(toDoEntity = toDoEntity)
    }
}