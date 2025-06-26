package com.example.pet.data.repository

import com.example.pet.data.network.MockWearableRemoteDataSource
import com.example.pet.data.network.WalkData
import com.example.pet.domain.repository.PetRepository
import com.example.pet.domain.model.HealthData
import com.example.pet.domain.model.RecommendedPlace
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val mockWearableRemoteDataSource: MockWearableRemoteDataSource
) : PetRepository {

    override fun startWalkingSimulation(): Flow<WalkData> {
        return mockWearableRemoteDataSource.startWalkingSimulation()
    }

    override fun stopWalkingSimulation() {
        mockWearableRemoteDataSource.stopWalkingSimulation()
    }

    override fun getRecommendedPlaces(): List<RecommendedPlace> {
        return mockWearableRemoteDataSource.getRecommendedPlaces()
    }

    override fun getHealthData(): HealthData {
        return mockWearableRemoteDataSource.getHealthData()
    }
}
