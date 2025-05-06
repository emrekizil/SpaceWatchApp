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


val satelliteListJson = """
            [
                {"id": 1, "name": "Starship-1", "active": false},
                {"id": 2, "name": "Dragon-1", "active": true},
                {"id": 3, "name": "Starship-3", "active": true}
            ]
        """.trimIndent()

val satelliteDetailJson = """
            [
                {"id": 1, "name": "Starship-1", "cost_per_launch": 1000000, "first_flight": "2020-01-01", "height": 100, "mass": 5000},
                {"id": 2, "name": "Dragon-1", "cost_per_launch": 2000000, "first_flight": "2021-02-02", "height": 150, "mass": 7000}
            ]
        """.trimIndent()

val positionsJson = """
            {
                "list": [
                    {
                        "id": "1",
                        "positions": [
                            {"posX": 0.864, "posY": 0.123},
                            {"posX": 0.765, "posY": 0.234}
                        ]
                    },
                    {
                        "id": "2",
                        "positions": [
                            {"posX": 0.543, "posY": 0.432},
                            {"posX": 0.654, "posY": 0.321}
                        ]
                    }
                ]
            }
        """.trimIndent()

