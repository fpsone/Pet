package com.petcare.presentation.ui.home.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.petcare.presentation.ui.common.GlassmorphismCard

@Composable
fun AIDiaryWidget(onAiDiaryClicked: () -> Unit) {
    GlassmorphismCard(
        modifier = Modifier
            .aspectRatio(2f)
            .clickable { onAiDiaryClicked() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = Icons.Rounded.Book, contentDescription = "AI Diary")
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "AI Diary", style = MaterialTheme.typography.titleLarge)
        }
    }
}