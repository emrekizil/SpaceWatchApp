package com.emrekizil.data.di

import com.emrekizil.data.service.SatelliteService
import com.emrekizil.data.service.SatelliteServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    @Singleton
    abstract fun bindSatelliteService(satelliteServiceImpl: SatelliteServiceImpl): SatelliteService
}