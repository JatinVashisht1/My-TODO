package com.example.mytodo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mytodo.core.Screen
import com.example.mytodo.presentation.add_edit_to_do_screen.AddEditToDoScreen
import com.example.mytodo.presentation.to_do_list_screen.ToDoListScreen
import com.example.mytodo.presentation.ui.theme.MyTODOTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyTODOTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
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
                                navArgument(name = "id"){
                                    type  = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ){
                            AddEditToDoScreen(context = this@MainActivity, navController = navController)
                        }
                    }
                }
            }
        }
    }
}