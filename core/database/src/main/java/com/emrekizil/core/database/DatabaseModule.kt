package com.emrekizil.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideSwDatabase(@ApplicationContext appContext: Context): SpaceWatchDatabase {
        return Room.databaseBuilder(
            context = appContext,
            SpaceWatchDatabase::class.java,
            "space_watch_database"
        )
            .fallbackToDestructiveMigrationFrom()
            .build()
    }

    @Provides
    @Singleton
    fun provideSwDao(spaceWatchDatabase: SpaceWatchDatabase): SpaceWatchDao =
        spaceWatchDatabase.spaceWatchDao()
}