package com.example.mytodo.presentation.add_edit_to_do_screen.components

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.add_edit_to_do_screen.AddEditToDoViewModel

@Composable
fun TaskAndDetails(
    task: ToDoEntity,
    viewModel: AddEditToDoViewModel,

) {
    OutlinedTextField(
        value = task.task,
        onValueChange = {
            viewModel.updatedNote.value = ToDoEntity(
                task = it,
                id = task.id,
                detail = task.detail,
                isCompleted = task.isCompleted,
                pin = task.pin,
                timeStamp = System.currentTimeMillis(),
                deadLineDate = task.deadLineDate,
                deadLineTime = task.deadLineTime,
                isWeekly = task.isWeekly,
                isMonthly = task.isMonthly
            )
        },
        placeholder = { Text(text = "Enter task") },
        modifier = Modifier.fillMaxWidth().focusable(false).padding(8.dp),
    )

    OutlinedTextField(
        value = task.detail,
        onValueChange = {
            viewModel.updatedNote.value = ToDoEntity(
                task = task.task,
                id = task.id,
                detail = it,
                isCompleted = task.isCompleted,
                pin = task.pin,
                timeStamp = System.currentTimeMillis(),
                deadLineDate = task.deadLineDate,
                deadLineTime = task.deadLineTime,
                isWeekly = task.isWeekly,
                isMonthly = task.isMonthly

            )
        },
        placeholder = {
            Text(text = "Enter details")
        },
        modifier = Modifier
            .fillMaxWidth()
            .focusable(false)
            .padding(8.dp),
    )
}