package com.example.mytodo.presentation.to_do_list_screen.components.to_do_item_components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.mytodo.core.Screen
import com.example.mytodo.domain.model.ToDoEntity

@Composable
fun EditToDoComponent(
    navHostController: NavController,
    toDoItem: ToDoEntity
) {
    IconButton(
        onClick = { navHostController.navigate(Screen.AddEditScreen.route + "?id=${toDoItem.id}") },
//        modifier = Modifier.align(Alignment.CenterVertically)
    ) {
        Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
    }
}