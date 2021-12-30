package com.example.mytodo.presentation.to_do_list_screen.components

import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.to_do_list_screen.ToDoListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CustomCheckBox(
    toDoItem: ToDoEntity,
    viewModel: ToDoListViewModel,
    scope: CoroutineScope,
    modifier: Modifier
) {
    Checkbox(
        checked = toDoItem.isCompleted,
        onCheckedChange = {
            scope.launch(Dispatchers.IO) {
                viewModel.insertToDo(
                    ToDoEntity(
                        isCompleted = it,
                        task = toDoItem.task,
                        detail = toDoItem.detail,
                        id = toDoItem.id,
                        pin = toDoItem.pin,
                        timeStamp = toDoItem.timeStamp,
                        deadLineDate = toDoItem.deadLineDate,
                        deadLineTime = toDoItem.deadLineTime,
                        isWeekly = toDoItem.isWeekly,
                        isMonthly = toDoItem.isMonthly
                    )
                )
            }

        },
        modifier = modifier
    )
}