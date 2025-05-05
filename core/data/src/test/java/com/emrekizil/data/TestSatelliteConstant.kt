package com.emrekizil.data

import com.emrekizil.core.database.SatelliteDetailEntity
import com.emrekizil.core.model.SatelliteDetail
import com.emrekizil.data.service.model.Position
import com.emrekizil.data.service.model.SatelliteDetailResponseItem
import com.emrekizil.data.service.model.SatelliteListResponseItem

fun satelliteDetailEntity() = SatelliteDetailEntity(
    id = 2,
    costPerLaunch = 7200000,
    firstFlight = "2025",
    height = 400,
    mass = 500
)

fun satelliteDetail() = SatelliteDetail(
    id = 2,
    costPerLaunch = 7200000,
    firstFlight = "2025",
    height = 400,
    mass = 500
)

fun satelliteListResponseItem() = SatelliteListResponseItem(
    active = false,
    name = "Starship-1",
    id = 1
)

fun satelliteDetailResponseItem() = SatelliteDetailResponseItem(
    id = 2,
    costPerLaunch = 7200000,
    firstFlight = "2025",
    height = 400,
    mass = 500,
)

fun position() = Position(0.1, 0.1)

const val SAMPLE_SATELLITE_ID = 2
const val SAMPLE_SATELLITE_NAME = "Starship-1"
