package com.petcare.presentation.ui.walk

sealed class WalkEvent {
    data object ToggleWalkingState : WalkEvent()
} 