package com.petcare.presentation.ui.health

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.petcare.data.remote.Appointment
import com.petcare.data.remote.Medication
import com.petcare.presentation.ui.common.GlassmorphismCard
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HealthScreen(viewModel: HealthViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                LongTermTrendsCard()
            }
            item {
                WeightManagementCard(
                    currentWeight = state.currentWeight,
                    onAddWeight = { weight ->
                        viewModel.onEvent(HealthEvent.AddWeightEntry(weight, java.util.Date()))
                    }
                )
            }
            item {
                MedicationsCard(
                    medications = state.medications,
                    onLogDose = { medication ->
                        viewModel.onEvent(HealthEvent.LogMedicationDose(medication))
                    }
                )
            }
            item {
                AppointmentsCard(
                    upcoming = state.upcomingAppointments,
                    past = state.pastAppointments
                )
            }
        }
    }
}

@Composable
private fun LongTermTrendsCard() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Activity", "Sleep", "Heart Rate")

    GlassmorphismCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                Text("Chart Placeholder")
            }
        }
    }
}

@Composable
private fun WeightManagementCard(
    currentWeight: Double?,
    onAddWeight: (Double) -> Unit
) {
    GlassmorphismCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Weight Management", style = MaterialTheme.typography.titleLarge)
            Text(
                "Current: ${currentWeight?.let { String.format("%.1f kg", it) } ?: "N/A"}",
                style = MaterialTheme.typography.headlineMedium
            )
            Box(modifier = Modifier.fillMaxWidth().height(150.dp), contentAlignment = Alignment.Center) {
                Text("Chart Placeholder")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { onAddWeight(72.5) /* Mock weight */ }, modifier = Modifier.align(Alignment.End)) {
                Text("Add Entry")
            }
        }
    }
}

@Composable
private fun MedicationsCard(medications: List<Medication>, onLogDose: (Medication) -> Unit) {
    val dateFormat = remember { SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault()) }
    GlassmorphismCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Medications", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            medications.forEach { medication ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(medication.name, style = MaterialTheme.typography.bodyLarge)
                        Text(
                            "Next: ${dateFormat.format(medication.nextDue)}",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    OutlinedButton(onClick = { onLogDose(medication) }) {
                        Text("Log Dose")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
private fun AppointmentsCard(upcoming: List<Appointment>, past: List<Appointment>) {
    val dateFormat = remember { SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault()) }
    GlassmorphismCard(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Appointments & Logs", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Upcoming", style = MaterialTheme.typography.titleMedium)
            LazyColumn(modifier = Modifier.height(150.dp)) {
                items(upcoming) { appointment ->
                    Text("${appointment.type} on ${dateFormat.format(appointment.date)}")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Past", style = MaterialTheme.typography.titleMedium)
            LazyColumn(modifier = Modifier.height(150.dp)) {
                items(past) { appointment ->
                    Text("${appointment.type} on ${dateFormat.format(appointment.date)}")
                }
            }
        }
    }
}
