package com.example.mytodo.presentation.add_edit_to_do_screen

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytodo.presentation.add_edit_to_do_screen.components.*
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

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
            Box(modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)){
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Text(text = "Weekly")
                }
            }
        },
        scaffoldState = bottomSheetState,
        sheetPeekHeight = 0.dp,
        sheetElevation = 16.dp,
        sheetShape = RoundedCornerShape(16.dp),
        sheetBackgroundColor = if (isSystemInDarkTheme()) Color.Black else Color(
            0xFF7C25FA
        )
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    backgroundColor = if (isSystemInDarkTheme()) Color.Black else Color(
                        0xFF7C25FA
                    )
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
            Column(modifier = Modifier.fillMaxSize()) {
                TaskAndDetails(task = task, viewModel = viewModel)

                Surface(
                    color = if (isSystemInDarkTheme()) Color(183, 28, 28, 255) else Color(
                        255,
                        224,
                        130,
                        255
                    ),
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
                            viewModel = viewModel,
                            task = task,
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