package com.example.pet.presentation.ui.health

import androidx.lifecycle.ViewModel
import com.example.pet.domain.model.HealthData
import com.example.pet.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HealthViewModel @Inject constructor(
    private val petRepository: PetRepository
) : ViewModel() {

    fun getHealthData(): HealthData {
        return petRepository.getHealthData()
    }
}
