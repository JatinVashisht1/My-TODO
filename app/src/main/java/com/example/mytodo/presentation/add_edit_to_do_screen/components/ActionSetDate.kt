package com.example.mytodo.presentation.add_edit_to_do_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.add_edit_to_do_screen.AddEditToDoViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker

@Composable
fun ActionSetDate(
    dateDialogState: MaterialDialogState,
    viewModel: AddEditToDoViewModel,
    toDoEntity: ToDoEntity
) {
    val date = rememberSaveable { mutableStateOf<String>("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { dateDialogState.show() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.DateRange,
            contentDescription = null,
            modifier = Modifier.padding(20.dp)
        )
        Text("Set Date")
    }

    MaterialDialog(dialogState = dateDialogState,
        buttons = {
            positiveButton(
                "Okay",
                onClick = {
                    viewModel.updatedNote.value = ToDoEntity(
                        isCompleted = toDoEntity.isCompleted,
                        task = toDoEntity.task,
                        detail = toDoEntity.detail,
                        pin = toDoEntity.pin,
                        timeStamp = toDoEntity.timeStamp,
                        id = toDoEntity.id,
                        deadLineTime = toDoEntity.deadLineTime,
                        deadLineDate = date.value,
                        isWeekly = toDoEntity.isWeekly,
                        isMonthly = toDoEntity.isMonthly,
                    )
                }
            )
            negativeButton("Cancel")
        }) {
        datepicker() {
            date.value = "${it.dayOfMonth}/${it.monthValue}/${it.year}"
        }
    }
}