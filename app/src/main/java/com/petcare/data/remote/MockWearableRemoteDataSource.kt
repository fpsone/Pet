package com.petcare.data.remote

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date
import kotlin.random.Random

data class WalkData(
    val latitude: Double,
    val longitude: Double,
    val distance: Double,
    val duration: Long
)

data class RecommendedPlace(
    val name: String,
    val description: String,
    val rating: Double,
    val imageUrl: String
)

data class HealthData(
    val weeklyActivity: List<Pair<String, Int>>,
    val weeklySleep: List<Pair<String, Int>>,
    val weightEntries: List<Pair<Date, Double>>,
    val medications: List<Medication>,
    val appointments: List<Appointment>
)

data class Medication(
    val name: String,
    val dosage: String,
    val nextDue: Date
)

data class Appointment(
    val type: String,
    val date: Date,
    val notes: String? = null
)

class MockWearableRemoteDataSource {

    private var isWalking = false

    fun startWalkingSimulation(): Flow<WalkData> = flow {
        isWalking = true
        var distance = 0.0
        var duration = 0L
        var lat = 34.0522
        var lon = -118.2437

        while (isWalking) {
            delay(2000)
            distance += 0.01
            duration += 2
            lat += (Random.nextDouble() - 0.5) / 1000
            lon += (Random.nextDouble() - 0.5) / 1000
            emit(WalkData(lat, lon, distance, duration))
        }
    }

    fun stopWalkingSimulation() {
        isWalking = false
    }

    fun getRecommendedPlaces(): List<RecommendedPlace> {
        return listOf(
            RecommendedPlace("Runyon Canyon Park", "Popular hiking trail with city views.", 4.7, "https://images.unsplash.com/photo-1589951392429-a893839e8c46?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            RecommendedPlace("Griffith Park", "Large park with trails, observatory & zoo.", 4.8, "https://images.unsplash.com/photo-1551632811-561732d1e306?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"),
            RecommendedPlace("The Original Dog Beach", "Off-leash dog beach in San Diego.", 4.9, "https://images.unsplash.com/photo-1549294413-322227abc3b7?q=80&w=1934&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D")
        )
    }

    fun getHealthData(): HealthData {
        val weeklyActivity = (0..24).map { "Week $it" to Random.nextInt(10, 20) }
        val weeklySleep = (0..24).map { "Week $it" to Random.nextInt(40, 60) }
        val weightEntries = (0..10).map { Date(System.currentTimeMillis() - it * 1000L * 60 * 60 * 24 * 7) to Random.nextDouble(20.0, 22.0) }
        val medications = listOf(
            Medication("Heartworm Prevention", "1 tablet", Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 15)),
            Medication("Flea & Tick", "1 topical application", Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 5))
        )
        val appointments = listOf(
            Appointment("Annual Check-up", Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30)),
            Appointment("Grooming", Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 10), "Requested a short summer cut.")
        )

        return HealthData(weeklyActivity, weeklySleep, weightEntries, medications, appointments)
    }
}
