package com.example.pet.presentation.ui.health

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MedicationsCard(medications: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            medications.forEach {
                Text(text = it)
            }
        }
    }
}
