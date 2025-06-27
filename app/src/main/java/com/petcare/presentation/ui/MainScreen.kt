package com.petcare.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.petcare.presentation.ui.common.AuroraBackground
import com.petcare.presentation.ui.common.BottomNavigationBar

@Composable
fun MainScreen(rootNavController: NavHostController, onThemeChange: (Boolean) -> Unit) {
    val mainNavController = rememberNavController()
    Box {
        AuroraBackground()
        Scaffold(
            bottomBar = { BottomNavigationBar(navController = mainNavController) },
            containerColor = Color.Transparent,
            content = {
                Box(modifier = Modifier.padding(it)) {
                    AppNavigation(
                        rootNavController = rootNavController,
                        mainNavController = mainNavController,
                        onThemeChange = onThemeChange
                    )
                }
            }
        )
    }
}