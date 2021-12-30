package com.example.mytodo.presentation.add_edit_to_do_screen.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytodo.domain.model.InvalidNoteException
import com.example.mytodo.presentation.add_edit_to_do_screen.AddEditToDoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun CustomTopAppBar(
    viewModel: AddEditToDoViewModel,
    scope: CoroutineScope,
    navController: NavController,
    context: Context
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .clickable { navController.navigateUp() }
                .padding(8.dp),
            tint = Color.White
        )

        Text(
            text = "Add/Edit ToDo",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = Color.White,
            modifier = Modifier.padding(start = 8.dp)
        )
        IconButton(
            onClick = {
                scope.launch {
                    try {
                        viewModel.save()
                        navController.navigateUp()
                    } catch (e: InvalidNoteException) {
                        Toast.makeText(
                            context,
                            "Empty task is not allowed",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Save,
                contentDescription = "Save Note",
                tint = Color.White
            )
        }
    }
}