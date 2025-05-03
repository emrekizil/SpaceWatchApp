package com.emrekizil.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SatelliteDetailEntity::class], version = 1, exportSchema = false)
abstract class SpaceWatchDatabase : RoomDatabase() {
    abstract fun spaceWatchDao(): SpaceWatchDao
}