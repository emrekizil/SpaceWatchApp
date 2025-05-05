package com.emrekizil.core.testing

import com.emrekizil.core.common.DataSource
import com.emrekizil.core.model.Satellite
import com.emrekizil.core.model.SatelliteDetail
import com.emrekizil.core.model.SatellitePosition
import com.emrekizil.data.repository.SpaceWatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class TestSpaceWatchRepository : SpaceWatchRepository {

    private var testRequest = TestResponseEnum.LOADING

    fun setRequest(testRequest: TestResponseEnum) {
        this.testRequest = testRequest
    }

    override fun getSatellites(searchQuery: String): Flow<DataSource<List<Satellite>>> =
        flow {
            emit(DataSource.Loading)
            when (testRequest) {
                TestResponseEnum.ERROR   -> emit(DataSource.Error(Exception()))
                TestResponseEnum.LOADING -> emit(DataSource.Loading)
                TestResponseEnum.SUCCESS -> emit(DataSource.Success(satelliteList()))
            }
        }

    override fun getSatelliteDetail(satelliteId: Int): Flow<DataSource<SatelliteDetail>> = flow {
        emit(DataSource.Loading)
        when (testRequest) {
            TestResponseEnum.ERROR   -> emit(DataSource.Error(Exception()))
            TestResponseEnum.LOADING -> emit(DataSource.Loading)
            TestResponseEnum.SUCCESS -> emit(DataSource.Success(satelliteDetail()))
        }
    }

    override fun getSatellitePosition(satelliteId: Int): Flow<DataSource<List<SatellitePosition>>> =
        flow {
            emit(DataSource.Loading)
            when (testRequest) {
                TestResponseEnum.ERROR   -> emit(DataSource.Error(Exception()))
                TestResponseEnum.LOADING -> emit(DataSource.Loading)
                TestResponseEnum.SUCCESS -> emit(DataSource.Success(satellitePositionList()))
            }
        }
}

enum class TestResponseEnum{
    ERROR,
    LOADING,
    SUCCESS
}


fun satelliteList() = listOf(satellite())
fun satellitePositionList() = listOf(satellitePosition())

fun satellitePosition() = SatellitePosition(0.1, 0.1)

fun satellite() = Satellite(
    active = true,
    id = 1,
    name = "Starship-1"
)

fun satelliteDetail() = SatelliteDetail(
    id = 2,
    costPerLaunch = 7200000,
    firstFlight = "2025",
    height = 400,
    mass = 500
)