package com.example.mytodo.presentation.task_weekly

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytodo.core.Screen
import com.example.mytodo.presentation.to_do_list_screen.ToDoListViewModel
import com.example.mytodo.presentation.to_do_list_screen.components.ToDoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TaskWeeklyScreen(
    viewModel: ToDoListViewModel = hiltViewModel(),
    navController: NavController
) {

    val result = viewModel.toDoState.value
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {

            FloatingActionButton(
                onClick = {
                    CoroutineScope(Dispatchers.Main).launch {
                        navController.navigate(Screen.AddEditScreen.route){
                            launchSingleTop = true
                        }
                    }
                },
                backgroundColor = if (isSystemInDarkTheme()) Color.DarkGray else Color.LightGray,
                modifier = Modifier.padding(bottom = 42.dp)

            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = null,
                )
            }
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = if (isSystemInDarkTheme()) Color(19, 22, 44, 255) else Color(
                        140,
                        158,
                        255,
                        255
                    )
                )
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(result.toDoList) { item ->
                    if (item.isWeekly) {
                        ToDoItem(
                            modifier = Modifier.fillMaxWidth(),
                            toDoItem = item,
                            viewModel = viewModel,
                            navHostController = navController
                        )
                    }
                }
            }
        }
    }
}