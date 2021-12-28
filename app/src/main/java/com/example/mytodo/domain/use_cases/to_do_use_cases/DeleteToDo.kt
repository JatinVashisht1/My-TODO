package com.example.mytodo.domain.use_cases.to_do_use_cases

import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.domain.repository.ToDoRepository

class DeleteToDo(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(toDoEntity: ToDoEntity){
        repository.deleteToDo(toDoEntity = toDoEntity)
    }
}