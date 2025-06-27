package com.petcare.presentation.ui.home.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.petcare.presentation.ui.common.glassmorphism
import androidx.compose.ui.graphics.Color

@Composable
fun LiveActivityWidget() {
    Box(
        modifier = Modifier
            .aspectRatio(2f)
            .glassmorphism()
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Live Activity Chart Placeholder", color = Color.White)
        }
    }
}