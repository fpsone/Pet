package com.petcare.presentation.ui.common

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AuroraBackground(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()

    val color1 by infiniteTransition.animateColor(
        initialValue = Color(0xFF007BFF).copy(alpha = 0.3f),
        targetValue = Color(0xFF8A2BE2).copy(alpha = 0.3f),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 10000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val color2 by infiniteTransition.animateColor(
        initialValue = Color(0xFF00FF7F).copy(alpha = 0.3f),
        targetValue = Color(0xFFFFA500).copy(alpha = 0.3f),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 12000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val xOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 15000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val yOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 18000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(color1, color2, Color.Transparent),
                        center = Offset(xOffset * 1000, yOffset * 1000),
                        radius = 1000f
                    )
                )
        )
    }
}
