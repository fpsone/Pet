package com.petcare.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petcare.presentation.ui.chat.ChatScreen

@Composable
fun RootNavigation(onThemeChange: (Boolean) -> Unit) {
    val rootNavController = rememberNavController()
    NavHost(navController = rootNavController, startDestination = "main") {
        composable("main") {
            MainScreen(rootNavController = rootNavController, onThemeChange = onThemeChange)
        }
        composable("chat") {
            ChatScreen()
        }
    }
}