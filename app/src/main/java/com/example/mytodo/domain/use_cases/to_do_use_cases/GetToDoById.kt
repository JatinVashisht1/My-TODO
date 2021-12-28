package com.example.mytodo.domain.use_cases.to_do_use_cases

import com.example.mytodo.domain.repository.ToDoRepository

class GetToDoById(
    private val repository: ToDoRepository
) {
    suspend operator fun invoke(id: Int) = repository.getToDoById(id = id)
}