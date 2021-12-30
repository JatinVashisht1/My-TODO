package com.example.mytodo.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarViewMonth
import androidx.compose.material.icons.outlined.CalendarViewWeek
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String = "", val icon: ImageVector?){
    object HomeScreen : Screen("HomeScreen", "Today", icon = Icons.Outlined.Home)
    object AddEditScreen : Screen("AddEditScreen", "", null)
    object TaskWeekly : Screen("TaskWeeklyScreen", "Weekly Tasks", Icons.Outlined.CalendarViewWeek, )
    object TaskMonthly : Screen("TaskMonthlyScreen","Monthly Tasks", Icons.Outlined.CalendarViewMonth)
}
