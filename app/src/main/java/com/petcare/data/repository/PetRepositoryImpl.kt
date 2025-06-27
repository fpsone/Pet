package com.petcare.data.repository

import com.petcare.domain.model.Pet
import com.petcare.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor() : PetRepository {
    override fun getPet(): Flow<Pet> {
        // In a real app, this would fetch data from a local or remote source.
        return flowOf(Pet(name = "Max", breed = "Golden Retriever"))
    }
}
