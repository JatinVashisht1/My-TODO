package com.example.mytodo.presentation.to_do_list_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mytodo.core.Screen
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.to_do_list_screen.ToDoListViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat

@Composable
fun ToDoItem(
    modifier: Modifier = Modifier,
    toDoItem: ToDoEntity,
    viewModel: ToDoListViewModel,
    navHostController: NavController
) {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    val scope = rememberCoroutineScope()
    Surface(
        color = if (isSystemInDarkTheme()) Color(183, 28, 28, 255) else Color(255, 224, 130, 255),
        modifier = modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    scope.launch {
                        viewModel.insertToDo(
                            ToDoEntity(
                                isCompleted = !toDoItem.isCompleted,
                                task = toDoItem.task,
                                detail = toDoItem.detail,
                                id = toDoItem.id,
                                pin = toDoItem.pin,
                                timeStamp = toDoItem.timeStamp
                            )
                        )
                    }
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(
                    text =  "Last Updated: " + DateFormat.getInstance().format(toDoItem.timeStamp),
                    style = MaterialTheme.typography.body2,
                            modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Row(modifier = Modifier.wrapContentWidth()) {
                        CustomCheckBox(
                            toDoItem = toDoItem,
                            viewModel = viewModel,
                            scope = scope,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(8.dp)
                        )

                        Column(modifier = Modifier.padding(8.dp)) {
                            Row(
//                        modifier = Modifier.fillMaxWidth()
                                verticalAlignment = Alignment.CenterVertically
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
                                Text(
                                    text = toDoItem.detail,
                                    fontWeight = FontWeight.Normal,
                                    maxLines = 7,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }

                    Row {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    viewModel.deleteToDo(toDoItem)
                                }
                            },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
                        }

                        IconButton(
                            onClick = {
                                scope.launch(IO) {
                                    viewModel.insertToDo(
                                        ToDoEntity(
                                            isCompleted = toDoItem.isCompleted,
                                            task = toDoItem.task,
                                            detail = toDoItem.detail,
                                            pin = !toDoItem.pin,
                                            id = toDoItem.id,
                                            timeStamp = toDoItem.timeStamp
                                        )
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (!toDoItem.pin) Icons.Outlined.StarBorder else Icons.Outlined.Star,
                                contentDescription = null
                            )
                        }

                        IconButton(
                            onClick = { navHostController.navigate(Screen.AddEditScreen.route + "?id=${toDoItem.id}") },
                            modifier = Modifier.align(Alignment.CenterVertically)
                        ) {
                            Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}
