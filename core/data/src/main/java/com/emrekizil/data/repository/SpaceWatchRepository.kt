package com.emrekizil.data.repository

import com.emrekizil.core.common.DataSource
import com.emrekizil.core.model.SatelliteListResponse
import kotlinx.coroutines.flow.Flow


interface SpaceWatchRepository {
    fun getSatellites(): Flow<DataSource<SatelliteListResponse>>
}