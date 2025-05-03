package com.emrekizil.data.repository

import com.emrekizil.core.common.DataSource
import com.emrekizil.core.model.Satellite
import com.emrekizil.core.model.SatelliteDetail
import com.emrekizil.core.model.SatellitePosition
import kotlinx.coroutines.flow.Flow


interface SpaceWatchRepository {
    fun getSatellites(searchQuery:String): Flow<DataSource<List<Satellite>>>
    fun getSatelliteDetail(satelliteId:Int):Flow<DataSource<SatelliteDetail>>
    fun getSatellitePosition(satelliteId:Int):Flow<DataSource<SatellitePosition>>
}