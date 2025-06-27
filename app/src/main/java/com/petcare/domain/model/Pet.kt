package com.petcare.domain.model

data class Pet(
    val name: String,
    val breed: String,
    val status: PetStatus = PetStatus.Idle
)

enum class PetStatus {
    Idle,
    Walking,
    Sleeping,
    Happy
}
