package com.petcare.presentation.ui.health

import com.petcare.data.remote.Appointment
import com.petcare.data.remote.Medication
import java.util.Date

data class HealthState(
    val currentWeight: Double? = null,
    val medications: List<Medication> = emptyList(),
    val upcomingAppointments: List<Appointment> = emptyList(),
    val pastAppointments: List<Appointment> = emptyList(),
    val isLoading: Boolean = true
) 