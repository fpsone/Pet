package com.example.pet.presentation.ui.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SettingsScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            PetProfileSetting(onClick = { /*TODO*/ })
        }
        item {
            ThemeSetting(onClick = { /*TODO*/ })
        }
    }
}
