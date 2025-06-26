package com.example.pet.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pet.presentation.ui.chat.ChatScreen
import com.example.pet.presentation.ui.explore.ExploreScreen
import com.example.pet.presentation.ui.health.HealthScreen
import com.example.pet.presentation.ui.home.HomeScreen
import com.example.pet.presentation.ui.settings.SettingsScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") {
            HomeScreen()
        }
        composable("health") {
            HealthScreen()
        }
        composable("explore") {
            ExploreScreen()
        }
        composable("settings") {
            SettingsScreen()
        }
        composable("chat") {
            ChatScreen()
        }
        // TODO: Add other destinations here
    }
}
