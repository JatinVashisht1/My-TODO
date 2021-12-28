package com.example.mytodo.domain.use_cases.to_do_use_cases

import com.example.mytodo.domain.model.InvalidNoteException
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.domain.repository.ToDoRepository
import kotlin.jvm.Throws

class InsertToDo(
    private val repository: ToDoRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(toDoEntity: ToDoEntity){
        if(toDoEntity.task.isBlank()){
            throw InvalidNoteException("Empty task is not allowed")
        }
        repository.insertToDo(toDoEntity = toDoEntity)
    }
}