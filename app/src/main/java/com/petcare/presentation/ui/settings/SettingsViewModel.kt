package com.petcare.presentation.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petcare.domain.usecase.GetPetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getPetUseCase: GetPetUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        getPetUseCase().onEach { pet ->
            _state.update { it.copy(pet = pet) }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnThemeChanged -> {
                _state.update { it.copy(isDarkMode = event.isDark) }
            }
        }
    }
} 