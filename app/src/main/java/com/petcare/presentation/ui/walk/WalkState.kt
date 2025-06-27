package com.petcare.presentation.ui.walk

import com.google.android.gms.maps.model.LatLng
import com.petcare.data.remote.RecommendedPlace

data class WalkState(
    val isWalking: Boolean = false,
    val scentTrail: List<LatLng> = emptyList(),
    val currentDuration: Long = 0L,
    val currentDistance: Double = 0.0,
    val recommendedPlaces: List<RecommendedPlace> = emptyList(),
    val isLoading: Boolean = true
) 