package com.petcare.presentation.ui.health

import com.petcare.data.remote.Medication
import java.util.Date

sealed class HealthEvent {
    data class LogMedicationDose(val medication: Medication) : HealthEvent()
    data class AddWeightEntry(val weight: Double, val date: Date) : HealthEvent()
} 