package com.petcare.presentation.ui.home.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.petcare.domain.model.Pet
import com.petcare.presentation.ui.common.glassmorphism
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color

@Composable
fun HeroWidget(pet: Pet?) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .glassmorphism()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = pet?.name ?: "Loading...",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Text(
                text = pet?.breed ?: "",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}