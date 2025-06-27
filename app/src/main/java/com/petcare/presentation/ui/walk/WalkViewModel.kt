package com.petcare.presentation.ui.walk

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.petcare.domain.repository.WearableRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalkViewModel @Inject constructor(
    private val wearableRepository: WearableRepository
) : ViewModel() {
    private val _state = MutableStateFlow(WalkState())
    val state = _state.asStateFlow()

    private var walkingJob: Job? = null

    init {
        loadRecommendedPlaces()
    }

    fun onEvent(event: WalkEvent) {
        when (event) {
            WalkEvent.ToggleWalkingState -> {
                if (_state.value.isWalking) {
                    stopWalking()
                } else {
                    startWalking()
                }
            }
        }
    }

    private fun startWalking() {
        _state.update { it.copy(isWalking = true, scentTrail = emptyList(), currentDistance = 0.0, currentDuration = 0L) }
        walkingJob = wearableRepository.startWalkingSimulation()
            .onEach { walkData ->
                _state.update {
                    it.copy(
                        scentTrail = it.scentTrail + LatLng(walkData.latitude, walkData.longitude),
                        currentDistance = walkData.distance,
                        currentDuration = walkData.duration
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun stopWalking() {
        walkingJob?.cancel()
        wearableRepository.stopWalkingSimulation()
        _state.update { it.copy(isWalking = false) }
    }

    private fun loadRecommendedPlaces() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val places = wearableRepository.getRecommendedPlaces()
            _state.update { it.copy(recommendedPlaces = places, isLoading = false) }
        }
    }
}
