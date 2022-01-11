package com.example.mytodo.presentation.to_do_list_screen.components.to_do_item_components

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytodo.domain.model.ToDoEntity
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalTime
import kotlin.math.pow

@Composable
fun LastUpdatedAndDeadline(
    toDoItem: ToDoEntity
) {
    Log.d(
        "HomeScreen",
        "date is ${
            DateFormat.getInstance().format(
                toDoItem.deadLineDate * (24 * 60 * 60 * (10.0.pow(3.0)).toLong()) + toDoItem.deadLineTime - 19800 * 10.0.pow(3)//19800*10.0.pow(3) is subtracted because of 
            )
        }"
    )
    Text(
        text = "Last Updated: " + DateFormat.getInstance().format(toDoItem.timeStamp),
        style = MaterialTheme.typography.body2,
        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
    )
    Text(
        text = "DeadLine: ${LocalDate.ofEpochDay(toDoItem.deadLineDate)} ${
            LocalTime.ofSecondOfDay(
                toDoItem.deadLineTime.toLong()
            )
        }",
        style = MaterialTheme.typography.body2,
        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
    )


}