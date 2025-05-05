package com.emrekizil.data.repository

import com.emrekizil.data.position
import com.emrekizil.data.satelliteDetailResponseItem
import com.emrekizil.data.satelliteListResponseItem
import com.emrekizil.data.service.SatelliteService
import com.emrekizil.data.service.model.Position
import com.emrekizil.data.service.model.SatelliteDetailResponseItem
import com.emrekizil.data.service.model.SatelliteListResponse

class FakeSatelliteService : SatelliteService {

    override suspend fun getSatellites(searchQuery: String): SatelliteListResponse {
        return listOf(
            satelliteListResponseItem(),
            satelliteListResponseItem().copy(name = "Starship-3")
        ).filter {
            it.name.lowercase().contains(searchQuery)
        }
    }

    override suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailResponseItem {
        return satelliteDetailResponseItem().copy(id = satelliteId)
    }

    override suspend fun getSatellitePosition(satelliteId: Int): List<Position> {
        return listOf(position(), position().copy(posX = 0.2))
    }

}