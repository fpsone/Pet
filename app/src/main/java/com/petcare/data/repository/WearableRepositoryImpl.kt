package com.petcare.data.repository

import com.petcare.data.remote.HealthData
import com.petcare.data.remote.MockWearableRemoteDataSource
import com.petcare.data.remote.RecommendedPlace
import com.petcare.data.remote.WalkData
import com.petcare.domain.repository.WearableRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WearableRepositoryImpl @Inject constructor(
    private val remoteDataSource: MockWearableRemoteDataSource
) : WearableRepository {

    override fun startWalkingSimulation(): Flow<WalkData> {
        return remoteDataSource.startWalkingSimulation()
    }

    override fun stopWalkingSimulation() {
        remoteDataSource.stopWalkingSimulation()
    }

    override fun getRecommendedPlaces(): List<RecommendedPlace> {
        return remoteDataSource.getRecommendedPlaces()
    }

    override fun getHealthData(): HealthData {
        return remoteDataSource.getHealthData()
    }
}
