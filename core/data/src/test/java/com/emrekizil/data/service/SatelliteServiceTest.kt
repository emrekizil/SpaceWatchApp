package com.emrekizil.data.service

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
class SatelliteServiceTest {

    private lateinit var service: SatelliteService
    private lateinit var assetManager: SwAssetManager
    private val testDispatcher = UnconfinedTestDispatcher()
    private val json = Json { ignoreUnknownKeys = true }

    @Before
    fun setup() {
        assetManager = mock()
        service = SatelliteService(testDispatcher, json, assetManager)
    }

    @Test
    fun `getSatellites returns filtered satellites based on search query`() = runTest {
        val satelliteListJson = """
            [
                {"id": 1, "name": "Starship-1", "active": false},
                {"id": 2, "name": "Dragon-1", "active": true},
                {"id": 3, "name": "Starship-3", "active": true}
            ]
        """.trimIndent()
        whenever(assetManager.open("satellite-list.json")).thenReturn(
            satelliteListJson.byteInputStream()
        )
        val result = service.getSatellites("alpha")
        assertEquals(2, result.size)
        assertEquals("Alpha Satellite", result[0].name)
        assertEquals("Gamma Alpha", result[1].name)
    }

    @Test
    fun `getSatelliteDetail returns detail for specified satellite ID`() = runTest {
        val satelliteDetailJson = """
            [
                {"id": 1, "name": "Alpha Satellite", "cost_per_launch": 1000000, "first_flight": "2020-01-01", "height": 100, "mass": 5000},
                {"id": 2, "name": "Beta Satellite", "cost_per_launch": 2000000, "first_flight": "2021-02-02", "height": 150, "mass": 7000}
            ]
        """.trimIndent()
        
        whenever(assetManager.open("satellite-detail.json")).thenReturn(
            satelliteDetailJson.byteInputStream()
        )
        val result = service.getSatelliteDetail(2)
        assertEquals(2, result.id)
        assertEquals(2000000, result.costPerLaunch)
    }

    @Test
    fun `getSatellitePosition returns positions for specified satellite ID`() = runTest {
        val positionsJson = """
            {
                "list": [
                    {
                        "id": "1",
                        "positions": [
                            {"posX": 0.864, "posY": 0.123},
                            {"posX": 0.765, "posY": 0.234}
                        ]
                    },
                    {
                        "id": "2",
                        "positions": [
                            {"posX": 0.543, "posY": 0.432},
                            {"posX": 0.654, "posY": 0.321}
                        ]
                    }
                ]
            }
        """.trimIndent()
        
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
    fun `getSatelliteDetail throws exception when satellite ID not found`() = runTest {

        val satelliteDetailJson = """
            [
                {"id": 1, "name": "Alpha Satellite", "cost_per_launch": 1000000, "first_flight": "2020-01-01", "height": 100, "mass": 5000}
            ]
        """.trimIndent()
        
        whenever(assetManager.open("satellite-detail.json")).thenReturn(
            satelliteDetailJson.byteInputStream()
        )
        service.getSatelliteDetail(999)
    }

    @Test(expected = NoSuchElementException::class)
    fun `getSatellitePosition throws exception when satellite ID not found`() = runTest {

        val positionsJson = """
            {
                "list": [
                    {
                        "id": "1",
                        "positions": [
                            {"posX": 0.864, "posY": 0.123}
                        ]
                    }
                ]
            }
        """.trimIndent()
        
        whenever(assetManager.open("positions.json")).thenReturn(
            positionsJson.byteInputStream()
        )

        service.getSatellitePosition(999)
    }
} 