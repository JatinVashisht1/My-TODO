package com.example.mytodo.presentation.to_do_list_screen.components.to_do_item_components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.Composable
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.to_do_list_screen.ToDoListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@Composable
fun InsertToDoButton(
    scope: CoroutineScope,
    viewModel: ToDoListViewModel,
    toDoItem: ToDoEntity
) {
    IconButton(
        onClick = {
            scope.launch(IO) {
                viewModel.insertToDo(
                    ToDoEntity(
                        isCompleted = toDoItem.isCompleted,
                        task = toDoItem.task,
                        detail = toDoItem.detail,
                        pin = !toDoItem.pin,
                        id = toDoItem.id,
                        timeStamp = toDoItem.timeStamp,
                        deadLineTime = toDoItem.deadLineTime,
                        deadLineDate = toDoItem.deadLineDate,
                        isMonthly = toDoItem.isMonthly,
                        isWeekly = toDoItem.isWeekly
                    )
                )
            }
        }
    ) {
        Icon(
            imageVector = if (!toDoItem.pin) Icons.Outlined.StarBorder else Icons.Outlined.Star,
            contentDescription = null
        )
    }
}