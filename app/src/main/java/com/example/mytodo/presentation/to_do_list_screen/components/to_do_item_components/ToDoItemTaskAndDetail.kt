package com.example.mytodo.presentation.to_do_list_screen.components.to_do_item_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mytodo.domain.model.ToDoEntity

@Composable
fun ToDoItemTaskAndDetail(
    toDoItem: ToDoEntity
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(0.5f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = toDoItem.task,
                style = TextStyle(textDecoration = if (toDoItem.isCompleted) TextDecoration.LineThrough else null),
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.h6.fontSize,
                color = if (toDoItem.isCompleted) Color.Gray else MaterialTheme.colors.primary,

                )
        }
        if (!toDoItem.isCompleted) {
            if (toDoItem.detail.isNotBlank()) {
                Text(
                    text = toDoItem.detail,
                    fontWeight = FontWeight.Normal,
                    maxLines = 7,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            }
        }
    }

}