package com.example.pet.presentation.ui.health

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HealthScreen(viewModel: HealthViewModel = hiltViewModel()) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            LongTermTrendsCard(data = viewModel.getHealthData().weeklyActivity)
        }
        item {
            WeightManagementCard(data = viewModel.getHealthData().weightEntries)
        }
        item {
            MedicationsCard(medications = viewModel.getHealthData().medications)
        }
        item {
            AppointmentsCard(appointments = viewModel.getHealthData().appointments)
        }
    }
}
