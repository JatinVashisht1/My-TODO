package com.example.mytodo.presentation.add_edit_to_do_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.add_edit_to_do_screen.AddEditToDoViewModel

@Composable
fun ActionWeekly(
    viewModel: AddEditToDoViewModel,
    task: ToDoEntity,

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                viewModel.updatedNote.value = ToDoEntity(
                    task = task.task,
                    id = task.id,
                    detail = task.detail,
                    isCompleted = task.isCompleted,
                    pin = task.pin,
                    timeStamp = System.currentTimeMillis(),
                    deadLineDate = task.deadLineDate,
                    deadLineTime = task.deadLineTime,
                    isWeekly =  !task.isWeekly,
                    isMonthly = task.isMonthly
                )
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = task.isWeekly,
            onCheckedChange = {
                viewModel.updatedNote.value = ToDoEntity(
                    task = task.task,
                    id = task.id,
                    detail = task.detail,
                    isCompleted = task.isCompleted,
                    pin = task.pin,
                    deadLineTime = task.deadLineTime,
                    timeStamp = System.currentTimeMillis(),
                    deadLineDate = task.deadLineDate,
                    isWeekly = it,
                    isMonthly = task.isMonthly
                )
            },
            modifier = Modifier.padding(8.dp),
        )

        Text("Weekly")
    }

}