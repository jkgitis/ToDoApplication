package com.example.todoapplication.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object AddEditTask : Screen("add_edit_task")
}
