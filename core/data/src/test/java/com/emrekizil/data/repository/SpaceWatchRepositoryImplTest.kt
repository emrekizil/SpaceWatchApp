package com.emrekizil.data.repository

import app.cash.turbine.test
import com.emrekizil.core.common.DataSource
import com.emrekizil.data.SAMPLE_SATELLITE_ID
import com.emrekizil.data.SAMPLE_SATELLITE_NAME
import com.emrekizil.data.mapper.asExternalModel
import com.emrekizil.data.satelliteDetail
import com.emrekizil.data.satelliteDetailEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SpaceWatchRepositoryImplTest {

    private lateinit var repository: SpaceWatchRepositoryImpl
    private lateinit var fakeSatelliteService: FakeSatelliteService
    private lateinit var fakeSpaceWatchDao: FakeSpaceWatchDao

    @Before
    fun setup() {
        fakeSatelliteService = FakeSatelliteService()
        fakeSpaceWatchDao = FakeSpaceWatchDao()
        repository = SpaceWatchRepositoryImpl(
            satelliteService = fakeSatelliteService,
            spaceWatchDao = fakeSpaceWatchDao,
            ioDispatcher = Dispatchers.IO
        )
    }

    @Test
    fun spaceWatchRepository_get_satellite_detail_is_fetch_from_db() = runTest {
        val database = fakeSpaceWatchDao.getSatelliteById(1).first()?.asExternalModel()
        val data = repository.getSatelliteDetail(1).last()
        assertThat((data as DataSource.Success).data).isEqualTo(database)
    }

    @Test
    fun spaceWatchRepository_get_satellite_detail_is_set_to_loading_state() = runTest {
        repository.getSatelliteDetail(SAMPLE_SATELLITE_ID).test {
            assertThat(awaitItem()).isInstanceOf(DataSource.Loading::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun spaceWatchRepository_get_satellite_detail_is_set_to_success_state() = runTest {
        fakeSpaceWatchDao.saveSatelliteDetail(satelliteDetailEntity())
        repository.getSatelliteDetail(SAMPLE_SATELLITE_ID).test {
            assertThat(awaitItem()).isInstanceOf(DataSource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(DataSource.Success::class.java)
            awaitComplete()
        }
    }

    @Test
    fun spaceWatchRepository_get_satellite_detail_is_emit_true_value() = runTest {
        fakeSpaceWatchDao.saveSatelliteDetail(satelliteDetailEntity())
        repository.getSatelliteDetail(SAMPLE_SATELLITE_ID).test {
            assertThat(awaitItem()).isInstanceOf(DataSource.Loading::class.java)
            assertThat(awaitItem()).isEqualTo(DataSource.Success(satelliteDetail()))
            awaitComplete()
        }
    }

    @Test
    fun spaceWatchRepository_get_satellites_is_set_to_loading_state() = runTest {
        repository.getSatellites(SAMPLE_SATELLITE_NAME).test {
            assertThat(awaitItem()).isInstanceOf(DataSource.Loading::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun spaceWatchRepository_get_satellites_is_set_to_success_state() = runTest {
        repository.getSatellites(SAMPLE_SATELLITE_NAME).test {
            assertThat(awaitItem()).isInstanceOf(DataSource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(DataSource.Success::class.java)
            awaitComplete()
        }
    }


    @Test
    fun spaceWatchRepository_get_position_is_set_to_loading_state() = runTest {
        repository.getSatellitePosition(SAMPLE_SATELLITE_ID).test {
            assertThat(awaitItem()).isInstanceOf(DataSource.Loading::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun spaceWatchRepository_get_position_is_set_to_success_state() = runTest {
        repository.getSatellitePosition(SAMPLE_SATELLITE_ID).test {
            assertThat(awaitItem()).isInstanceOf(DataSource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(DataSource.Success::class.java)
            awaitComplete()
        }
    }
} 