package com.example.mytodo.domain.use_cases.to_do_use_cases

import com.example.mytodo.core.Resources
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class GetAllToDo(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(): Flow<List<ToDoEntity>>{
        return repository.getAllToDo()
    }
}
