package com.example.mytodo.presentation.add_edit_to_do_screen.components

import android.util.Log
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
import java.text.DateFormat
import kotlin.math.pow

@Composable
fun ActionSetDate(
    dateDialogState: MaterialDialogState,
    viewModel: AddEditToDoViewModel,
    toDoEntity: ToDoEntity
) {
    val date = rememberSaveable { mutableStateOf<Long>(0) }

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
        datepicker{
            var a = it.toEpochDay()
            a *= 24*60*60*(10.0.pow(3.0)).toLong()
            a -= (5*60*60*(10.0.pow(3.0)) + 30*60*(10.0.pow(3.0))).toLong()
            Log.d("HomeScreen", "nanoseconds value: $a and value in date is ${DateFormat.getInstance().format(a)}")
            date.value = it.toEpochDay()
        }
    }
}