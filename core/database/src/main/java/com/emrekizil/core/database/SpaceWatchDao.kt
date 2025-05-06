package com.emrekizil.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SpaceWatchDao {
    @Query("SELECT * FROM satellitedetailentity")
    fun getSatellites(): Flow<List<SatelliteDetailEntity>>

    @Query("SELECT * FROM satellitedetailentity WHERE id = :satelliteId")
    fun getSatelliteById(satelliteId: Int): Flow<SatelliteDetailEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSatelliteDetail(satelliteDetailEntity: SatelliteDetailEntity)
}