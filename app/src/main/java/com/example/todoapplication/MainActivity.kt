package com.example.todoapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.todoapplication.presentation.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoApplicationApp()
        }
    }
}

@Composable
fun TodoApplicationApp() {
    Surface(
        color = Color.White
    ) {
        val navController = rememberNavController()
        AppNavGraph(navController = navController)
    }
}
