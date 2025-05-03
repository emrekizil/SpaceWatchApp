package com.emrekizil.data.repository

import android.util.Log
import com.emrekizil.core.common.DataSource
import com.emrekizil.core.model.Satellite
import com.emrekizil.core.model.SatelliteDetail
import com.emrekizil.core.model.SatellitePosition
import com.emrekizil.data.service.SatelliteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SpaceWatchRepositoryImpl @Inject constructor(
    private val satelliteService: SatelliteService
) : SpaceWatchRepository {
    override fun getSatellites(searchQuery:String): Flow<DataSource<List<Satellite>>> = flow {
        emit(DataSource.Loading)
        Log.d("Lovingg", "loading")
        try {
            val data: List<Satellite> = satelliteService.getSatellites(searchQuery).map {
                Satellite(
                    active = it.active,
                    id = it.id,
                    name = it.name
                )
            }
            Log.d("Lovingg", data.toString())
            emit(DataSource.Success(data))
        } catch (e: Exception) {
            Log.d("Lovingg", e.toString())
        }
    }.catch {
        Log.d("Lovingg", "error")
        emit(DataSource.Error(Exception()))
    }

    override fun getSatelliteDetail(satelliteId: Int): Flow<DataSource<SatelliteDetail>> = flow {
        emit(DataSource.Loading)
        try {
            val serviceData = satelliteService.getSatelliteDetail(satelliteId)
            val data = SatelliteDetail(
                costPerLaunch = serviceData.costPerLaunch,
                firstFlight = serviceData.firstFlight,
                height = serviceData.height,
                id = serviceData.id,
                mass = serviceData.mass
            )
            Log.d("Lovingg2", data.toString())
            emit(DataSource.Success(data))
        }catch (e:Exception){
            Log.d("Lovingg2", e.toString())
        }

    }.catch {
        emit(DataSource.Error(Exception()))
    }

    override fun getSatellitePosition(satelliteId: Int): Flow<DataSource<List<SatellitePosition>>> = flow {
        emit(DataSource.Loading)
        val serviceData = satelliteService.getSatellitePosition(satelliteId).map {
            SatellitePosition(
                posX = it.posX,
                posY = it.posY
            )
        }
        emit(DataSource.Success(serviceData))
    }.catch {
        emit(DataSource.Error(Exception()))
    }
}