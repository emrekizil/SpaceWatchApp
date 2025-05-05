package com.emrekizil.data.repository

import com.emrekizil.core.database.SatelliteDetailEntity
import com.emrekizil.core.database.SpaceWatchDao
import com.emrekizil.data.satelliteDetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeSpaceWatchDao : SpaceWatchDao {

    private val satellites = MutableStateFlow<List<SatelliteDetailEntity>>(listOf(
        satelliteDetailEntity().copy(id = 1)
    ))

    override fun getSatellites(): Flow<List<SatelliteDetailEntity>> =
        satellites

    override fun getSatelliteById(satelliteId: Int): Flow<SatelliteDetailEntity> =
        satellites.map { data->
            data.first {
                it.id == satelliteId
            }
        }

    override suspend fun saveSatelliteDetail(satelliteDetailEntity: SatelliteDetailEntity) {
        satellites.update {
            it + satelliteDetailEntity
        }
    }
}