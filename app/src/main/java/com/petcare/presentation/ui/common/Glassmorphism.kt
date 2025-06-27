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
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.glassmorphism(
    shape: Shape = RoundedCornerShape(16.dp),
    blur: Dp = 10.dp,
): Modifier = composed {
    val blurRadiusPx = with(androidx.compose.ui.platform.LocalDensity.current) { blur.toPx() }

    this
        .graphicsLayer {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                renderEffect = android.graphics.RenderEffect
                    .createBlurEffect(
                        blurRadiusPx,
                        blurRadiusPx,
                        android.graphics.Shader.TileMode.DECAL
                    )
                    .asComposeRenderEffect()
            }
        }
        .background(
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
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
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        // This is the background with the blur effect.
        // It's a separate Box from the content.
        Box(modifier = Modifier.matchParentSize().glassmorphism(shape = shape))
        // The content is placed on top and will not be blurred.
        content()
    }
}
