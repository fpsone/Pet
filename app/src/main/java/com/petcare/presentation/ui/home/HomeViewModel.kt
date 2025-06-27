package com.petcare.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.gms.maps.model.LatLng
import com.petcare.domain.usecase.GetPetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPetUseCase: GetPetUseCase,
    private val generativeModel: GenerativeModel
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        loadInitialData()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.AiDiaryClicked -> {
                _state.update { it.copy(isAiDiaryDialogVisible = true) }
                generateDiary()
            }

            HomeEvent.DismissAiDiaryDialog -> {
                _state.update { it.copy(isAiDiaryDialogVisible = false, aiDiaryText = "") }
            }
        }
    }

    private fun loadInitialData() {
        getPetUseCase().onEach { pet ->
            _state.update {
                it.copy(
                    pet = pet,
                    batteryPercentage = 85,
                    petLocation = LatLng(34.0522, -118.2437),
                    isLoading = false
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun generateDiary() {
        viewModelScope.launch {
            _state.update { it.copy(isDiaryLoading = true) }
            try {
                val pet = _state.value.pet
                val prompt =
                    "Write a short, playful diary entry from the perspective of a dog named ${pet?.name ?: "a dog"}. Today's activities included a walk, some playtime, and a nap. Mention something funny that happened."
                val response = generativeModel.generateContent(prompt)
                _state.update { it.copy(aiDiaryText = response.text ?: "", isDiaryLoading = false) }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        aiDiaryText = "Sorry, I couldn't write my diary right now. Please try again later.",
                        isDiaryLoading = false
                    )
                }
            }
        }
    }
}