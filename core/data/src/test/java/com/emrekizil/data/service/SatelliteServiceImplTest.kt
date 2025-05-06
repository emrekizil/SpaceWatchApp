package com.emrekizil.data.service

import com.emrekizil.data.positionsJson
import com.emrekizil.data.satelliteDetailJson
import com.emrekizil.data.satelliteListJson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class SatelliteServiceImplTest {

    private lateinit var service: SatelliteServiceImpl
    private lateinit var assetManager: SwAssetManager
    private val testDispatcher = UnconfinedTestDispatcher()
    private val json = Json { ignoreUnknownKeys = true }

    @Before
    fun setup() {
        assetManager = mock()
        service = SatelliteServiceImpl(testDispatcher, json, assetManager)
    }

    @Test
    fun getSatellites_returns_filteredSatellites_based_on_searchQuery() = runTest {

        whenever(assetManager.open("satellite-list.json")).thenReturn(
            satelliteListJson.byteInputStream()
        )

        val result = service.getSatellites("starship")

        assertEquals(2, result.size)
        assertEquals("Starship-1", result[0].name)
        assertEquals("Starship-3", result[1].name)
    }

    @Test
    fun getSatelliteDetail_returns_detail_for_specifiedSatelliteId() = runTest {

        whenever(assetManager.open("satellite-detail.json")).thenReturn(
            satelliteDetailJson.byteInputStream()
        )

        val result = service.getSatelliteDetail(2)

        assertEquals(2, result.id)
        assertEquals(2000000, result.costPerLaunch)
    }

    @Test
    fun getSatellitePosition_returns_positions_for_specifiedSatelliteId() = runTest {

        whenever(assetManager.open("positions.json")).thenReturn(
            positionsJson.byteInputStream()
        )

        val result = service.getSatellitePosition(2)

        assertEquals(2, result.size)
        assertEquals(0.543, result[0].posX, 0.001)
        assertEquals(0.432, result[0].posY, 0.001)
        assertEquals(0.654, result[1].posX, 0.001)
        assertEquals(0.321, result[1].posY, 0.001)
    }

    @Test(expected = NoSuchElementException::class)
    fun getSatelliteDetail_throws_exception_when_satelliteId_notFound() = runTest {

        whenever(assetManager.open("satellite-detail.json")).thenReturn(
            satelliteDetailJson.byteInputStream()
        )

        service.getSatelliteDetail(99)
    }

    @Test(expected = NoSuchElementException::class)
    fun getSatellitePosition_throws_exception_when_satelliteId_notFound() = runTest {

        whenever(assetManager.open("positions.json")).thenReturn(
            positionsJson.byteInputStream()
        )

        service.getSatellitePosition(99)
    }
} 