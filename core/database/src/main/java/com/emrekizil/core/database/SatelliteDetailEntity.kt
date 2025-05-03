package com.emrekizil.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SatelliteDetailEntity(
    @PrimaryKey
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int
)