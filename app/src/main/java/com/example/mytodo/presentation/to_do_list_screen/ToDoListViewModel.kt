package com.example.mytodo.presentation.to_do_list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytodo.core.Resources
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.domain.use_cases.to_do_use_cases.ToDoUseCases
import com.example.mytodo.presentation.to_do_list_screen.components.ToDoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val toDoUseCases: ToDoUseCases
) : ViewModel() {
    private var getNotesJob: Job? = null
    private val _toDoState = mutableStateOf<ToDoState>(ToDoState())
    val toDoState = _toDoState

    private val _toDoStateWeekly = mutableStateOf<ToDoState>(ToDoState())
    val toDoStateWeekly = _toDoStateWeekly

    var deletedNote: ToDoEntity? = null

    suspend fun restoreNote() {
        deletedNote?.let {
            insertToDo(deletedNote!!)
        }
    }

    init {
        viewModelScope.launch(IO) {
            getAllToDo()
        }
    }

    suspend fun insertToDo(toDoEntity: ToDoEntity) {
        toDoUseCases.insertToDo(toDoEntity = toDoEntity)
    }

    suspend fun deleteToDo(toDoEntity: ToDoEntity) {
        toDoUseCases.deleteToDo(toDoEntity = toDoEntity)
    }

    private suspend fun getAllToDo() {
        getNotesJob?.cancel()
        getNotesJob = toDoUseCases.getAllToDo()
            .onEach { notes ->
                _toDoState.value = toDoState.value.copy(
                    toDoList = notes.sortedByDescending { it.pin })
            }
            .launchIn(viewModelScope)
    }


    suspend fun deleteCompletedToDo() {
        toDoUseCases.deleteCompletedToDo()
    }
}
