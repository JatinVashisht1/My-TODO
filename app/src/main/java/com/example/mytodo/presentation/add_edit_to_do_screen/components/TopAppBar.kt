package com.example.mytodo.presentation.add_edit_to_do_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.mytodo.presentation.add_edit_to_do_screen.AddEditToDoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopAppBar(
    viewModel: AddEditToDoViewModel,
    modifier: Modifier,
    scope: CoroutineScope
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Add/Edit ToDo",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
        IconButton(
            onClick = {
                scope.launch {
                    viewModel.save()
                }
            }
        ) {
                Icon(imageVector = Icons.Outlined.Save, contentDescription = "Save Note")
        }
    }
}