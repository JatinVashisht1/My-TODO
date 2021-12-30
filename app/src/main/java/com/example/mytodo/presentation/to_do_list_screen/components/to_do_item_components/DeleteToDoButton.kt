package com.example.mytodo.presentation.to_do_list_screen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mytodo.domain.model.ToDoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DeleteToDoButton(
    viewModel: ToDoListViewModel,
    scope: CoroutineScope,
    modifier: Modifier,
    toDoItem: ToDoEntity
) {
    IconButton(
        onClick = {
            scope.launch {
                viewModel.deleteToDo(toDoItem)
            }
        },
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
    }
}