package com.example.mytodo.presentation.add_edit_to_do_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.domain.use_cases.to_do_use_cases.ToDoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditToDoViewModel @Inject constructor(
    private val useCases: ToDoUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _noteState = mutableStateOf<ToDoEntity>(ToDoEntity())
    val noteState = _noteState

    val updatedNote = _noteState

    init {
        savedStateHandle.get<Int>("id")?.let {
            if (it != -1) {
                viewModelScope.launch {
                    getToDoById(id = it)
                }
            }
        }
    }

    private suspend fun getToDoById(id: Int) {
        _noteState.value = useCases.getToDoById(id = id)!!
    }

    suspend fun insertToDo(toDoEntity: ToDoEntity) {
        useCases.insertToDo(toDoEntity = toDoEntity)
    }

    suspend fun save() {
//        if (!updatedNote.value.task.isNullOrEmpty()) {
        useCases.insertToDo(
            ToDoEntity(
                isCompleted = updatedNote.value.isCompleted,
                task = updatedNote.value.task.trim(),
                detail = updatedNote.value.detail.trim(),
                id = _noteState.value.id,
                pin = updatedNote.value.pin,
                timeStamp = System.currentTimeMillis()
            )
        )
    }
}