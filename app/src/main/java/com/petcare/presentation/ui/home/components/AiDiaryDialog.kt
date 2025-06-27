package com.petcare.presentation.ui.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.petcare.presentation.ui.common.GlassmorphismCard

@Composable
fun AiDiaryDialog(
    isDialogVisible: Boolean,
    isLoading: Boolean,
    diaryText: String,
    onDismiss: () -> Unit
) {
    if (isDialogVisible) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            GlassmorphismCard(modifier = Modifier.fillMaxSize(0.9f)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AnimatedVisibility(visible = isLoading, enter = fadeIn(), exit = fadeOut()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
                            Text(text = "Thinking about my day...", style = MaterialTheme.typography.titleLarge)
                        }
                    }
                    AnimatedVisibility(visible = !isLoading && diaryText.isNotEmpty(), enter = fadeIn(), exit = fadeOut()) {
                        Text(
                            text = diaryText,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
} 