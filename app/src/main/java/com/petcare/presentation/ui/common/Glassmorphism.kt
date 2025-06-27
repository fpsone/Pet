package com.petcare.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.glassmorphism(
    shape: Shape = RoundedCornerShape(16.dp),
    blur: Dp = 10.dp,
): Modifier = composed {
    this
        .blur(radius = blur)
        .clip(shape)
        .background(MaterialTheme.colorScheme.surface)
        .border(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = listOf(Color.White.copy(alpha = 0.2f), Color.White.copy(alpha = 0.1f))
            ),
            shape = shape
        )
}

@Composable
fun GlassmorphismCard(modifier: Modifier = Modifier, shape: Shape = RoundedCornerShape(16.dp), content: @Composable () -> Unit) {
    Box(
        modifier = modifier.glassmorphism(shape = shape)
    ) {
        content()
    }
}
