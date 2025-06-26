package com.example.pet.presentation.ui.explore

import androidx.lifecycle.ViewModel
import com.example.pet.domain.model.RecommendedPlace
import com.example.pet.domain.repository.PetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val petRepository: PetRepository
) : ViewModel() {

    fun getRecommendedPlaces(): List<RecommendedPlace> {
        return petRepository.getRecommendedPlaces()
    }
}
