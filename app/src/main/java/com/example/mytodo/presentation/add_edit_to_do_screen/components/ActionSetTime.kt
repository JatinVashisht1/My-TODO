package com.example.mytodo.presentation.add_edit_to_do_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Timer
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
import com.vanpra.composematerialdialogs.datetime.time.timepicker

@Composable
fun ActionSetTime(
    dialogState: MaterialDialogState,
    viewModel: AddEditToDoViewModel,
    toDoEntity: ToDoEntity
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { dialogState.show() }, verticalAlignment = Alignment.CenterVertically){
        Icon(Icons.Outlined.Timer, null, modifier = Modifier.padding(20.dp))
        Text("Set Time")
    }

    val time = rememberSaveable { mutableStateOf<Long>(0) }
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Okay", onClick = {
                viewModel.updatedNote.value = ToDoEntity(
                    isCompleted = toDoEntity.isCompleted,
                    task = toDoEntity.task,
                    detail = toDoEntity.detail,
                    pin = toDoEntity.pin,
                    timeStamp = toDoEntity.timeStamp,
                    id = toDoEntity.id,
                    deadLineTime = time.value,
                    deadLineDate = toDoEntity.deadLineDate,
                    isWeekly = toDoEntity.isWeekly,
                    isMonthly = toDoEntity.isMonthly,
                )
            })
            negativeButton("Cancel")
        },

        ) {
        timepicker() {
            time.value = it.toNanoOfDay()
        }
    }

}