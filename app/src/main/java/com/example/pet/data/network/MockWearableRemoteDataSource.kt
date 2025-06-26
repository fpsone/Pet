package com.example.pet.data.network

import com.example.pet.domain.model.HealthData
import com.example.pet.domain.model.RecommendedPlace
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

data class WalkData(
    val latitude: Double,
    val longitude: Double,
    val distance: Double,
    val duration: Long
)

class MockWearableRemoteDataSource {

    private var isWalking = false

    fun startWalkingSimulation(): Flow<WalkData> = flow {
        isWalking = true
        var distance = 0.0
        var duration = 0L
        while (isWalking) {
            delay(2000)
            distance += Random.nextDouble(0.01, 0.05)
            duration += 2
            emit(
                WalkData(
                    latitude = Random.nextDouble(30.0, 40.0),
                    longitude = Random.nextDouble(-120.0, -110.0),
                    distance = distance,
                    duration = duration
                )
            )
        }
    }

    fun stopWalkingSimulation() {
        isWalking = false
    }

    fun getRecommendedPlaces(): List<RecommendedPlace> {
        return listOf(
            RecommendedPlace(
                name = "Greenwood Park",
                description = "A beautiful park with a large dog run.",
                rating = 4.8f,
                imageUrl = "https://images.unsplash.com/photo-1589998059171-988d887df646?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"
            ),
            RecommendedPlace(
                name = "Ocean Beach",
                description = "A long, sandy beach that's perfect for a game of fetch.",
                rating = 4.9f,
                imageUrl = "https://images.unsplash.com/photo-1519046904884-53103b34b206?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"
            ),
            RecommendedPlace(
                name = "The Dog Cafe",
                description = "A cozy cafe that welcomes dogs of all sizes.",
                rating = 4.7f,
                imageUrl = "https://images.unsplash.com/photo-1554118811-1e0d58224f24?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"
            )
        )
    }

    fun getHealthData(): HealthData {
        return HealthData(
            weeklyActivity = (1..24).map { Random.nextInt(100, 500) },
            weightEntries = (1..10).map { Random.nextFloat() * 5 + 20 },
            medications = listOf("Heartworm prevention", "Flea and tick treatment"),
            appointments = listOf("Annual check-up", "Vaccination booster")
        )
    }

    // TODO: Add other mock data functions here
}
