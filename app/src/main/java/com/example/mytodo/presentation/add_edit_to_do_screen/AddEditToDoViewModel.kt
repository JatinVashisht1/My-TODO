package com.example.mytodo.presentation.add_edit_to_do_screen

import android.content.Context
import android.content.Intent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.MyAlarmService
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.domain.use_cases.to_do_use_cases.ToDoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddEditToDoViewModel @Inject constructor(
    private val useCases: ToDoUseCases,
    savedStateHandle: SavedStateHandle,
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

    @ExperimentalMaterialApi
    suspend fun save() {
        useCases.insertToDo(
            ToDoEntity(
                isCompleted = updatedNote.value.isCompleted,
                task = updatedNote.value.task.trim(),
                detail = updatedNote.value.detail.trim(),
                id = _noteState.value.id,
                pin = updatedNote.value.pin,
                timeStamp = System.currentTimeMillis(),
                deadLineTime = updatedNote.value.deadLineTime,
                deadLineDate = updatedNote.value.deadLineDate,
                isWeekly = updatedNote.value.isWeekly,
                isMonthly = updatedNote.value.isMonthly,
            )
        )
    }
}