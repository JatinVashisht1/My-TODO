package com.example.mytodo.presentation.to_do_list_screen.components.to_do_item_components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.to_do_list_screen.ToDoListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DeleteToDoButton(
    modifier: Modifier,
    onDeletePressed: ()->Unit
) {
    IconButton(
        onClick = onDeletePressed,
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
    }
}