package com.petcare.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.petcare.presentation.ui.common.AuroraBackground
import com.petcare.presentation.ui.common.BottomNavigationBar

@Composable
fun MainScreen(navController: NavHostController, onThemeChange: (Boolean) -> Unit) {
    Box {
        AuroraBackground()
        Scaffold(
            bottomBar = { BottomNavigationBar(navController = navController) },
            containerColor = Color.Transparent,
            content = {
                Box(modifier = Modifier.padding(it)) {
                    AppNavigation(navController = navController, onThemeChange = onThemeChange)
                }
            }
        )
    }
}