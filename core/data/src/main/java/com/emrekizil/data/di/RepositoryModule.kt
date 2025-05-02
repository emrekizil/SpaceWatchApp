package com.emrekizil.data.di

import com.emrekizil.data.repository.SpaceWatchRepository
import com.emrekizil.data.repository.SpaceWatchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindSpaceWatchRepository(spaceWatchRepositoryImpl: SpaceWatchRepositoryImpl): SpaceWatchRepository
}