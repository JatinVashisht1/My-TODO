package com.example.mytodo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CalendarViewMonth
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mytodo.core.Screen
import com.example.mytodo.presentation.add_edit_to_do_screen.AddEditToDoScreen
import com.example.mytodo.presentation.task_monthly.TaskMonthlyScreen
import com.example.mytodo.presentation.task_weekly.TaskWeeklyScreen
import com.example.mytodo.presentation.to_do_list_screen.ToDoListScreen
import com.example.mytodo.presentation.ui.theme.MyTODOTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            val items = listOf(Screen.HomeScreen, Screen.TaskWeekly, Screen.TaskMonthly)
            MyTODOTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        bottomBar = {
                            BottomNavigation {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentDestination = navBackStackEntry?.destination
                                items.forEach {screen ->  
                                    BottomNavigationItem(
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                        icon = { Icon(imageVector = screen.icon!!, contentDescription = null) },
                                        label = { Text(text = screen.label)},
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                // Pop up to the start destination of the graph to
                                                // avoid building up a large stack of destinations
                                                // on the back stack as users select items
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                // Avoid multiple copies of the same destination when
                                                // reselecting the same item
                                                launchSingleTop = true
                                                // Restore state when reselecting a previously selected item
                                                restoreState = true
                                            }
                                        },

                                    )
                                }
                            }
                        },

                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = Screen.HomeScreen.route
                        ) {
                            composable(Screen.HomeScreen.route) {
                                ToDoListScreen(navController = navController)
                            }
                            composable(
                                route = Screen.AddEditScreen.route + "?id={id}",
                                arguments = listOf(
                                    navArgument(name = "id") {
                                        type = NavType.IntType
                                        defaultValue = -1
                                    }
                                )
                            ) {
                                AddEditToDoScreen(
                                    context = this@MainActivity,
                                    navController = navController
                                )
                            }
                            composable(Screen.TaskWeekly.route) {
                                TaskWeeklyScreen(navController = navController)
                            }
                            composable(Screen.TaskMonthly.route){
                                TaskMonthlyScreen(navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}
