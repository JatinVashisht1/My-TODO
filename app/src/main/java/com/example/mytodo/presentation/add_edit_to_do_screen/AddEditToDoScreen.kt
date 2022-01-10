package com.example.mytodo.presentation.add_edit_to_do_screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.add_edit_to_do_screen.components.*
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AddEditToDoScreen(
    context: Context,
    navController: NavController,
    viewModel: AddEditToDoViewModel = hiltViewModel()
) {
    val dialogState = rememberMaterialDialogState()
    val dateDialogState = rememberMaterialDialogState()
    val a = rememberSaveable { mutableStateOf(false) }
    val toDoEntity = viewModel.noteState.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val task = viewModel.updatedNote.value

    val bottomSheetState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(200.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Text(
                        text = "Today",
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                scope.launch {
                                    viewModel.updatedNote.value =
                                        ToDoEntity(
                                            isCompleted = toDoEntity.isCompleted,
                                            task = toDoEntity.task,
                                            detail = toDoEntity.detail,
                                            pin = toDoEntity.pin,
                                            timeStamp = toDoEntity.timeStamp,
                                            deadLineTime = toDoEntity.deadLineTime,
                                            deadLineDate = toDoEntity.deadLineDate,
                                            isWeekly = false,
                                            isMonthly = false,
                                            id = toDoEntity.id,
                                        )

                                    bottomSheetState.bottomSheetState.collapse()
                                }
                            }
                            .padding(4.dp)
                    )

                    Divider(modifier = Modifier.padding(8.dp))

                    Text(
                        text = "Weekly",
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                scope.launch {
                                    viewModel.updatedNote.value =
                                        ToDoEntity(
                                            isCompleted = toDoEntity.isCompleted,
                                            task = toDoEntity.task,
                                            detail = toDoEntity.detail,
                                            pin = toDoEntity.pin,
                                            timeStamp = toDoEntity.timeStamp,
                                            deadLineTime = toDoEntity.deadLineTime,
                                            deadLineDate = toDoEntity.deadLineDate,
                                            isWeekly = true,
                                            isMonthly = false,
                                            id = toDoEntity.id,
                                        )
                                    bottomSheetState.bottomSheetState.collapse()
                                }
                            }
                            .padding(4.dp)
                    )
                    Divider(modifier = Modifier.padding(8.dp))
                    Text(
                        text = "Monthly",
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                scope.launch {
                                    viewModel.updatedNote.value =
                                        ToDoEntity(
                                            isCompleted = toDoEntity.isCompleted,
                                            task = toDoEntity.task,
                                            detail = toDoEntity.detail,
                                            pin = toDoEntity.pin,
                                            timeStamp = toDoEntity.timeStamp,
                                            deadLineTime = toDoEntity.deadLineTime,
                                            deadLineDate = toDoEntity.deadLineDate,
                                            isWeekly = false,
                                            isMonthly = true,
                                            id = toDoEntity.id,
                                        )

                                    bottomSheetState.bottomSheetState.collapse()
                                }
                            }
                            .padding(4.dp)
                    )


                }
            }
        },
        scaffoldState = bottomSheetState,
        sheetPeekHeight = 0.dp,
        sheetElevation = 16.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = if (isSystemInDarkTheme()) Color.Black else Color(0xFF7C25FA)
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color(0xFF7C25FA)
                ) {
                    CustomTopAppBar(
                        viewModel = viewModel,
                        scope = scope,

                        navController = navController,
                        context = context
                    )

                }
            },

            ) {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (isSystemInDarkTheme()) Color(
                        19,
                        22,
                        44,
                        255
                    ) else Color(140, 158, 255, 255)
                )
                .pointerInput(key1 = Unit) {
                    this.detectTapGestures(onTap = {
                        scope.launch {
                            bottomSheetState.bottomSheetState.collapse()
                        }
                    }
                    )
                }
            ) {
                item(1) {
                    TaskAndDetails(task = task, viewModel = viewModel)
                    Surface(
                        color = if (isSystemInDarkTheme()) Color(183, 28, 28, 255) else Color(255,224,130,255),
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Column {
                            ActionCompleted(viewModel = viewModel, task = task)
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            )

                            ActionPin(viewModel = viewModel, task = task)

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            )

                            ActionWeekly(
                                sheetState = bottomSheetState
                            )


                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            )

                            ActionSetTime(
                                dialogState = dialogState,
                                viewModel = viewModel,
                                toDoEntity = toDoEntity
                            )

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp)
                            )

                            ActionSetDate(
                                dateDialogState = dateDialogState,
                                viewModel = viewModel,
                                toDoEntity = toDoEntity
                            )

                        }
                    }
                }
            }
        }
    }
}