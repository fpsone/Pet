package com.petcare.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petcare.presentation.ui.chat.ChatScreen

@Composable
fun RootNavigation(onThemeChange: (Boolean) -> Unit) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController, onThemeChange = onThemeChange)
        }
        composable("chat") {
            ChatScreen()
        }
    }
}