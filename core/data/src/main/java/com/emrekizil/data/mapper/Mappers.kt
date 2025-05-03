package com.emrekizil.data.mapper

import com.emrekizil.core.database.SatelliteDetailEntity
import com.emrekizil.core.model.Satellite
import com.emrekizil.core.model.SatelliteDetail
import com.emrekizil.core.model.SatellitePosition
import com.emrekizil.data.service.model.Position
import com.emrekizil.data.service.model.SatelliteDetailResponseItem
import com.emrekizil.data.service.model.SatelliteListResponse

fun SatelliteListResponse.asExternalModel(): List<Satellite> =
    this.map {
        Satellite(
            active = it.active,
            id = it.id,
            name = it.name
        )
    }


typealias PositionResponse = List<Position>

fun PositionResponse.asExternalModel():List<SatellitePosition> =
    this.map {
        SatellitePosition(
            posX = it.posX,
            posY = it.posY
        )
    }

fun SatelliteDetailEntity.asExternalModel():SatelliteDetail =
    SatelliteDetail(
        costPerLaunch = this.costPerLaunch,
        firstFlight = this.firstFlight,
        height = this.height,
        id = this.id,
        mass = this.mass
    )

fun SatelliteDetailResponseItem.asEntity():SatelliteDetailEntity =
    SatelliteDetailEntity(
        costPerLaunch = this.costPerLaunch,
        firstFlight = this.firstFlight,
        height = this.height,
        id = this.id,
        mass = this.mass
    )