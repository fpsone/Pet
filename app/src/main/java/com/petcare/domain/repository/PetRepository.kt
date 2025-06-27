package com.petcare.domain.repository

import com.petcare.domain.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetRepository {
    fun getPet(): Flow<Pet>
} 