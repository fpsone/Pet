package com.petcare.presentation.ui.home

import com.google.android.gms.maps.model.LatLng
import com.petcare.domain.model.Pet

data class HomeState(
    val pet: Pet? = null,
    val batteryPercentage: Int = 0,
    val petLocation: LatLng? = null,
    val isAiDiaryDialogVisible: Boolean = false,
    val aiDiaryText: String = "",
    val isDiaryLoading: Boolean = false,
    val isLoading: Boolean = true
)
