package com.example.mytodo.presentation

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import androidx.core.app.AlarmManagerCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mytodo.MyAlarmService
import com.example.mytodo.core.Screen
import com.example.mytodo.presentation.add_edit_to_do_screen.AddEditToDoScreen
import com.example.mytodo.presentation.components.CustomBottomNavigation
import com.example.mytodo.presentation.task_monthly.TaskMonthlyScreen
import com.example.mytodo.presentation.task_weekly.TaskWeeklyScreen
import com.example.mytodo.presentation.to_do_list_screen.ToDoListScreen
import com.example.mytodo.presentation.ui.theme.MyTODOTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.time.LocalDate
import java.util.*

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            val items = listOf(Screen.HomeScreen, Screen.TaskWeekly, Screen.TaskMonthly)

            //Get alarm instance
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(this, MyAlarmService::class.java)
            //Intent Part
//            val intent = Intent(this, MyAlarmService::class.java)
//            intent.action = "FOO_ACTION"
            //Setting up pending intent
            val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

            //Alarm Time
//            val ALARM_DELAY_IN_SECOND = 10
//            val alarmTimeAtUtc = System.currentTimeMillis() + ALARM_DELAY_IN_SECOND*1000L

            /*
            Set with system Alarm Service
            Other possible functions: setExact() / setRepeating() / setWindow(), etc
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmTimeAtUtc, pendingIntent)
            */


            MyTODOTheme {
                // A surface container using the 'background' color from the theme

                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        scaffoldState = scaffoldState,
                        bottomBar = {
                            CustomBottomNavigation(navController = navController, items = items)
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
                                    navController = navController,

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
