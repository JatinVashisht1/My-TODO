package com.example.mytodo.presentation.add_edit_to_do_screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytodo.domain.model.InvalidNoteException
import com.example.mytodo.domain.model.ToDoEntity
import kotlinx.coroutines.launch

@Composable
fun AddEditToDoScreen(
    context: Context,
    navController: NavController,
    viewModel: AddEditToDoViewModel = hiltViewModel()
) {
    val toDoEntity = viewModel.noteState.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val task = viewModel.updatedNote.value
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color(0xFF7C25FA)) {
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
                            .padding(8.dp)
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
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = task.task,
                onValueChange = {
                    viewModel.updatedNote.value = ToDoEntity(
                        task = it,
                        id = task.id,
                        detail = task.detail,
                        isCompleted = task.isCompleted,
                        pin = task.pin,
                        timeStamp = System.currentTimeMillis()
                    )
                },
                placeholder = {
                    Text(text = "Enter task")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusable(false)
                    .padding(8.dp),
            )

            OutlinedTextField(
                value = task.detail,
                onValueChange = {
                    viewModel.updatedNote.value = ToDoEntity(
                        task = task.task,
                        id = task.id,
                        detail = it,
                        isCompleted = task.isCompleted,
                    )
                },
                placeholder = {
                    Text(text = "Enter details")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusable(false)
                    .padding(8.dp),
            )

            Surface(
                color = if (isSystemInDarkTheme()) Color(183, 28, 28, 255) else Color(
                    255,
                    224,
                    130,
                    255
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.updatedNote.value = ToDoEntity(
                                    task = task.task,
                                    id = task.id,
                                    detail = task.detail,
                                    isCompleted = !task.isCompleted,
                                    pin = task.pin
                                )
                            }, verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = task.isCompleted,
                            onCheckedChange = {
                                viewModel.updatedNote.value = ToDoEntity(
                                    task = task.task,
                                    id = task.id,
                                    detail = task.detail,
                                    isCompleted = it,
                                    pin = task.pin
                                )
                            },
                            modifier = Modifier.padding(8.dp),
                        )

                        Text("Completed")
                    }
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.updatedNote.value = ToDoEntity(
                                    task = task.task,
                                    id = task.id,
                                    detail = task.detail,
                                    isCompleted = task.isCompleted,
                                    pin = !task.pin
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Checkbox(
                            checked = task.pin,
                            onCheckedChange = {
                                viewModel.updatedNote.value = ToDoEntity(
                                    task = task.task,
                                    id = task.id,
                                    detail = task.detail,
                                    isCompleted = task.isCompleted,
                                    pin = it
                                )
                            },
                            modifier = Modifier.padding(8.dp),
                        )
                        Text("Pin")
                    }
                }
            }
        }
    }
}
