package com.petcare.presentation.ui.health

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petcare.domain.repository.WearableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HealthViewModel @Inject constructor(
    private val wearableRepository: WearableRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HealthState())
    val state = _state.asStateFlow()

    init {
        loadHealthData()
    }

    fun onEvent(event: HealthEvent) {
        when (event) {
            is HealthEvent.AddWeightEntry -> {
                // In a real app, you would save this to the repository
                _state.update { currentState ->
                    currentState.copy(
                        currentWeight = event.weight
                    )
                }
            }
            is HealthEvent.LogMedicationDose -> {
                // Logic to handle logging a dose would go here
            }
        }
    }

    private fun loadHealthData() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val healthData = wearableRepository.getHealthData()
            _state.update {
                it.copy(
                    currentWeight = healthData.weightEntries.lastOrNull()?.second,
                    medications = healthData.medications,
                    upcomingAppointments = healthData.appointments.filter { it.date.after(Date()) },
                    pastAppointments = healthData.appointments.filter { it.date.before(Date()) },
                    isLoading = false
                )
            }
        }
    }
}
