package com.example.todoapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todoapplication.presentation.screens.addtask.AddEditTaskScreen
import com.example.todoapplication.presentation.screens.home.HomeScreen
import com.example.todoapplication.presentation.screens.login.LoginScreen
import com.example.todoapplication.presentation.screens.register.RegisterScreen
import com.example.todoapplication.presentation.screens.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(route = Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screen.AddEditTask.route) {
            AddEditTaskScreen(navController)
        }
    }
}
