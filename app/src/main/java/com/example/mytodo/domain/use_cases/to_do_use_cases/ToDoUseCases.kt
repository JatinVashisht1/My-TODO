package com.example.mytodo.domain.use_cases.to_do_use_cases

data class ToDoUseCases(
    val insertToDo: InsertToDo,
    val deleteToDo: DeleteToDo,
    val deleteCompletedToDo: DeleteCompletedToDo,
    val getAllToDo: GetAllToDo,
    val getToDoById: GetToDoById,
)