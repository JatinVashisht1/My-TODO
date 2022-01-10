package com.example.mytodo.presentation.to_do_list_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mytodo.presentation.to_do_list_screen.ToDoListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

@Composable
fun CustomAlertDialogBox(
    alertDialogState: MutableState<Boolean>,
    viewModel: ToDoListViewModel,
    scope: CoroutineScope
) {
    AlertDialog(
        onDismissRequest = { alertDialogState.value = false },
        dismissButton = {
            Button(
                onClick = {
                          alertDialogState.value = false
                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(46, 125, 50, 255)
                )
            ) {
                Row {
                    Text(text = "Cancel", color = Color.White)
                    Icon(
                        imageVector = Icons.Outlined.Cancel,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    scope.launch(IO) {
                        viewModel.deleteCompletedToDo()
                    }
                    alertDialogState.value = false
                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(198, 40, 40, 255)
//                    backgroundColor = Color(46, 125, 50, 255)
                )
            ) {
                Row{
                    Text(text = "Delete", color = Color.White)
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        text = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Outlined.DeleteForever,
                    contentDescription = null,
                    tint = Color(198, 40, 40, 255),
                    modifier = Modifier.size(MaterialTheme.typography.h2.fontSize.value.dp)
                )
                Text(text = "Delete Completed Tasks?", style = MaterialTheme.typography.h6)
            }
        }
    )
}