package com.petcare.presentation.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Health,
        BottomNavItem.Explore,
        BottomNavItem.Settings
    )
    NavigationBar(
        modifier = Modifier.padding(16.dp).clip(RoundedCornerShape(24.dp)).glassmorphism(shape = RoundedCornerShape(24.dp)),
        containerColor = Color.Transparent
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = if (currentRoute == item.route) item.selectedIcon else item.unselectedIcon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

sealed class BottomNavItem(val route: String, val unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector, val selectedIcon: androidx.compose.ui.graphics.vector.ImageVector, val title: String) {
    object Home : BottomNavItem("home", Icons.Outlined.Home, Icons.Filled.Home, "Home")
    object Health : BottomNavItem("health", Icons.Outlined.FavoriteBorder, Icons.Filled.Favorite, "Health")
    object Explore : BottomNavItem("explore", Icons.Outlined.Place, Icons.Filled.Place, "Explore")
    object Settings : BottomNavItem("settings", Icons.Outlined.Settings, Icons.Filled.Settings, "Settings")
}
