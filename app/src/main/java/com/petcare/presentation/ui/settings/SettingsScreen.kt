package com.petcare.presentation.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.petcare.presentation.ui.common.GlassmorphismCard

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel(), onThemeChange: (Boolean) -> Unit) {
    val state by viewModel.state.collectAsState()

    // Pass the theme choice up to the main theme controller
    onThemeChange(state.isDarkMode)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            SettingsItem(
                icon = Icons.Filled.AccountCircle,
                title = state.pet?.name ?: "Pet Profile",
                subtitle = "View and edit your pet's details"
            )
        }
        item {
            SettingsItem(
                icon = Icons.Filled.Memory,
                title = "Device Management",
                subtitle = "Firmware: ${state.wearableFirmware}, Battery: ${state.wearableBattery}%"
            )
        }
        item {
            SettingsItem(icon = Icons.Filled.People, title = "The Pack", subtitle = "View your friends' activity")
        }
        item {
            ThemeToggle(isDarkMode = state.isDarkMode, onThemeSelected = {
                viewModel.onEvent(SettingsEvent.OnThemeChanged(it))
            })
        }
    }
}

@Composable
private fun SettingsItem(icon: ImageVector, title: String, subtitle: String) {
    GlassmorphismCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = title, modifier = Modifier.padding(end = 16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, style = MaterialTheme.typography.titleLarge)
                Text(text = subtitle, style = MaterialTheme.typography.bodyMedium)
            }
            Icon(imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = "Navigate")
        }
    }
}

@Composable
private fun ThemeToggle(isDarkMode: Boolean, onThemeSelected: (Boolean) -> Unit) {
    Column {
        Text("Theme", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(bottom = 8.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            ThemePreviewCard(
                title = "Light",
                icon = Icons.Filled.LightMode,
                isSelected = !isDarkMode,
                onClick = { onThemeSelected(false) }
            )
            ThemePreviewCard(
                title = "Dark",
                icon = Icons.Filled.DarkMode,
                isSelected = isDarkMode,
                onClick = { onThemeSelected(true) }
            )
        }
    }
}

@Composable
private fun ThemePreviewCard(title: String, icon: ImageVector, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(100.dp)
            .width(150.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title)
        }
    }
}
