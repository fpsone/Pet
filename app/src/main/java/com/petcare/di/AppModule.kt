package com.petcare.di

import com.petcare.data.remote.MockWearableRemoteDataSource
import com.petcare.data.repository.PetRepositoryImpl
import com.petcare.data.repository.WearableRepositoryImpl
import com.petcare.domain.repository.PetRepository
import com.petcare.domain.repository.WearableRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object {
        @Provides
        @Singleton
        fun provideMockWearableRemoteDataSource(): MockWearableRemoteDataSource {
            return MockWearableRemoteDataSource()
        }

        @Provides
        @Singleton
        fun provideWearableRepository(remoteDataSource: MockWearableRemoteDataSource): WearableRepository {
            return WearableRepositoryImpl(remoteDataSource)
        }
    }

    @Binds
    @Singleton
    abstract fun bindPetRepository(petRepositoryImpl: PetRepositoryImpl): PetRepository
}
