package com.example.pet.domain.model

data class HealthData(
    val weeklyActivity: List<Int>,
    val weightEntries: List<Float>,
    val medications: List<String>,
    val appointments: List<String>
)
