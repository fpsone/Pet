package com.petcare.domain.usecase

import com.petcare.domain.model.Pet
import com.petcare.domain.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPetUseCase @Inject constructor(
    private val petRepository: PetRepository
) {
    operator fun invoke(): Flow<Pet> {
        return petRepository.getPet()
    }
}
