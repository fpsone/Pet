package com.petcare.presentation.ui.settings

sealed class SettingsEvent {
    data class OnThemeChanged(val isDark: Boolean) : SettingsEvent()
} 