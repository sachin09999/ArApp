package com.sachin.arapp.di

import com.sachin.arapp.data.DrillRepository
import com.sachin.arapp.data.DrillRepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDrillRepository(): DrillRepository = DrillRepositoryImpl()

}