package com.emrekizil.data.service

import com.emrekizil.data.service.model.Position
import com.emrekizil.data.service.model.SatelliteDetailResponseItem
import com.emrekizil.data.service.model.SatelliteListResponse

interface SatelliteService {
    suspend fun getSatellites(searchQuery: String): SatelliteListResponse
    suspend fun getSatelliteDetail(satelliteId: Int): SatelliteDetailResponseItem
    suspend fun getSatellitePosition(satelliteId: Int): List<Position>
}