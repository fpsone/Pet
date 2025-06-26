package com.example.pet.di

import com.example.pet.data.network.MockWearableRemoteDataSource
import com.example.pet.data.repository.PetRepositoryImpl
import com.example.pet.domain.repository.PetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMockWearableRemoteDataSource(): MockWearableRemoteDataSource {
        return MockWearableRemoteDataSource()
    }

    @Provides
    @Singleton
    fun providePetRepository(mockWearableRemoteDataSource: MockWearableRemoteDataSource): PetRepository {
        return PetRepositoryImpl(mockWearableRemoteDataSource)
    }
}
