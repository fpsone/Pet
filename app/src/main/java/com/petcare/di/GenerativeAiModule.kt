package com.petcare.di

import com.google.ai.client.generativeai.GenerativeModel
import com.petcare.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GenerativeAiModule {

    @Provides
    @Singleton
    fun provideGenerativeModel(): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = BuildConfig.GEMINI_API_KEY
        )
    }
} 