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
fun AppNavigation(
    rootNavController: NavHostController,
    mainNavController: NavHostController,
    onThemeChange: (Boolean) -> Unit
) {
    NavHost(navController = mainNavController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = rootNavController)
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

