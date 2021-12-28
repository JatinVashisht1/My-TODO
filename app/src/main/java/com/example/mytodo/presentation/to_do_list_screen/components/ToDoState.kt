package com.example.mytodo.presentation.to_do_list_screen.components

import com.example.mytodo.domain.model.ToDoEntity

data class ToDoState(
    val isLoading: Boolean = false,
    val error: String = "",
    val toDoList: List<ToDoEntity> = listOf()
)