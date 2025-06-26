package com.example.pet.domain.repository

import com.example.pet.data.network.WalkData
import com.example.pet.domain.model.HealthData
import com.example.pet.domain.model.RecommendedPlace
import kotlinx.coroutines.flow.Flow

interface PetRepository {

    fun startWalkingSimulation(): Flow<WalkData>

    fun stopWalkingSimulation()

    fun getRecommendedPlaces(): List<RecommendedPlace>

    fun getHealthData(): HealthData
}
