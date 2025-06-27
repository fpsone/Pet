package com.petcare.presentation.ui.settings

import com.petcare.domain.model.Pet

data class SettingsState(
    val pet: Pet? = null,
    val wearableBattery: Int = 100,
    val wearableFirmware: String = "1.0.0",
    val isDarkMode: Boolean = false
) 