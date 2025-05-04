package com.emrekizil.core.database

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

internal class SpaceWatchDaoTest : DatabaseTest() {

    @Test
    fun getSatellites() = runTest {
        insertTopics()
        val savedSatellites = spaceWatchDao.getSatellites().first()
        assertEquals(
            listOf(1,2,3),
            savedSatellites.map { it.id }
        )
    }

    @Test
    fun getSatellite() = runTest {
        insertTopics()
        val savedSatellite = spaceWatchDao.getSatelliteById(3).first()

        assertEquals(
            expected = "2023",
            savedSatellite.firstFlight
        )
    }

    @Test
    fun insertTopic_newEntryIsReplacedIfAlreadyExists()= runTest {
        insertTopics()
        spaceWatchDao.saveSatelliteDetail(
            testSatelliteDetailEntity(1,"2021")
        )
        val newSavedTopic = spaceWatchDao.getSatelliteById(1).first()
        assertEquals(
            expected = "2021",
            newSavedTopic.firstFlight
        )
    }

    private suspend fun insertTopics() {
        spaceWatchDao.saveSatelliteDetail(testSatelliteDetailEntity(1, "2025"))
        spaceWatchDao.saveSatelliteDetail(testSatelliteDetailEntity(2, "2024"))
        spaceWatchDao.saveSatelliteDetail(testSatelliteDetailEntity(3, "2023"))
    }
}

private fun testSatelliteDetailEntity(
    id: Int = 0,
    firstFlight: String = "2025"
) =
    SatelliteDetailEntity(
        id = id,
        costPerLaunch = 0,
        firstFlight = firstFlight,
        height = 0,
        mass = 0
    )