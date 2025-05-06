package com.emrekizil.data.repository

import com.emrekizil.core.common.DataSource
import com.emrekizil.core.common.Dispatcher
import com.emrekizil.core.common.SwDispatchers
import com.emrekizil.core.database.SpaceWatchDao
import com.emrekizil.core.model.Satellite
import com.emrekizil.core.model.SatelliteDetail
import com.emrekizil.core.model.SatellitePosition
import com.emrekizil.data.mapper.asEntity
import com.emrekizil.data.mapper.asExternalModel
import com.emrekizil.data.mapper.asExternalSatelliteModel
import com.emrekizil.data.service.SatelliteService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SpaceWatchRepositoryImpl @Inject constructor(
    @Dispatcher(SwDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val satelliteService: SatelliteService,
    private val spaceWatchDao: SpaceWatchDao
) : SpaceWatchRepository {
    override fun getSatellites(searchQuery: String): Flow<DataSource<List<Satellite>>> = flow {
        emit(DataSource.Loading)
        val data = satelliteService.getSatellites(searchQuery).asExternalSatelliteModel()
        emit(DataSource.Success(data))
    }.catch { exception ->
        emit(DataSource.Error(exception))
    }.flowOn(ioDispatcher)

    override fun getSatelliteDetail(satelliteId: Int): Flow<DataSource<SatelliteDetail>> =
        flow {
            emit(DataSource.Loading)
            val dbData = spaceWatchDao.getSatelliteById(satelliteId).firstOrNull()
            dbData?.let { data ->
                emit(DataSource.Success(data.asExternalModel()))
            } ?: run {
                val entity = satelliteService.getSatelliteDetail(satelliteId).asEntity()
                spaceWatchDao.saveSatelliteDetail(entity)
                emit(DataSource.Success(entity.asExternalModel()))
            }
        }.catch { exception ->
            emit(DataSource.Error(exception))
        }.flowOn(ioDispatcher)

    override fun getSatellitePosition(satelliteId: Int): Flow<DataSource<List<SatellitePosition>>> =
        flow {
            emit(DataSource.Loading)
            val data = satelliteService.getSatellitePosition(satelliteId).asExternalModel()
            emit(DataSource.Success(data))
        }.catch { exception ->
            emit(DataSource.Error(exception))
        }.flowOn(ioDispatcher)
}

