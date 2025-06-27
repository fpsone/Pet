package com.petcare.domain.repository

import com.petcare.data.remote.HealthData
import com.petcare.data.remote.RecommendedPlace
import com.petcare.data.remote.WalkData
import kotlinx.coroutines.flow.Flow

interface WearableRepository {
    fun startWalkingSimulation(): Flow<WalkData>
    fun stopWalkingSimulation()
    fun getRecommendedPlaces(): List<RecommendedPlace>
    fun getHealthData(): HealthData
}
