package com.example.pet.presentation.ui.settings

import androidx.lifecycle.ViewModel
import com.example.pet.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val petRepository: PetRepository
) : ViewModel() {
    // TODO: Implement the viewmodel logic here
}
