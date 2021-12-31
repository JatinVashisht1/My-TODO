package com.example.mytodo.presentation.to_do_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytodo.core.Screen
import com.example.mytodo.domain.model.ToDoEntity
import com.example.mytodo.presentation.to_do_list_screen.components.CustomAlertDialogBox
import com.example.mytodo.presentation.to_do_list_screen.components.ToDoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ToDoListScreen(
    navController: NavController,
    viewModel: ToDoListViewModel = hiltViewModel(),
) {
    val todo = ToDoEntity(task = "First Task")
    val result = viewModel.toDoState.value
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val expanded = remember { mutableStateOf(false) }
    val alertDialogState = remember { mutableStateOf(false) }
    Scaffold(
        scaffoldState = scaffoldState,

        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = if (isSystemInDarkTheme()) Color.Black else Color(0xFF7C25FA)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "TODO List Screen",
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Icon(
                    Icons.Default.Delete,
                    null,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            alertDialogState.value = !alertDialogState.value
                        }
                        .padding(4.dp),
                    tint = Color(255, 0, 0, 255)
                )
                if (alertDialogState.value) {
                    CustomAlertDialogBox(alertDialogState, viewModel = viewModel, scope = scope)
                }
            }
        },
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
//            Text("Test Text", color = Color.White)
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(result.toDoList) { item ->
                    if (!item.isMonthly && !item.isWeekly)
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