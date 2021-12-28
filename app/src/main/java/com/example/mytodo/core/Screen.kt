package com.example.mytodo.core

sealed class Screen(val route: String){
    object HomeScreen : Screen("HomeScreen")
    object AddEditScreen : Screen("AddEditScreen")
}
