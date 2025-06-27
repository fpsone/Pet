package com.petcare.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.petcare.presentation.ui.health.HealthScreen
import com.petcare.presentation.ui.home.HomeScreen
import com.petcare.presentation.ui.settings.SettingsScreen
import com.petcare.presentation.ui.walk.WalkScreen

@Composable
fun AppNavigation(navController: NavHostController, onThemeChange: (Boolean) -> Unit) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("health") {
            HealthScreen()
        }
        composable("explore") {
            WalkScreen()
        }
        composable("settings") {
            SettingsScreen(onThemeChange = onThemeChange)
        }
    }
}

