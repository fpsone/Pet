package com.petcare.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.blur

fun Modifier.glassmorphism(
    shape: Shape,
    blur: Dp,
): Modifier = composed {
    this
        .blur(radius = blur)
        .background(
            color = MaterialTheme.colorScheme.surface,
            shape = shape
        )
        .border(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = listOf(Color.White.copy(alpha = 0.2f), Color.White.copy(alpha = 0.1f))
            ),
            shape = shape
        )
        .clip(shape)
}

@Composable
fun GlassmorphismCard(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(16.dp),
    blur: Dp = 10.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .glassmorphism(shape = shape, blur = blur)
        )
        content()
    }
}
