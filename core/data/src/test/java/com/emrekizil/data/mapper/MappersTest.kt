package com.emrekizil.data.mapper

import com.emrekizil.core.database.SatelliteDetailEntity
import com.emrekizil.core.model.Satellite
import com.emrekizil.core.model.SatellitePosition
import com.emrekizil.data.service.model.Position
import com.emrekizil.data.service.model.SatelliteDetailResponseItem
import com.emrekizil.data.service.model.SatelliteListResponseItem
import org.junit.Assert.assertEquals
import org.junit.Test

class MappersTest {

    @Test
    fun satellite_response_can_be_mapped_to_satellite_response() {
        val responseItems = listOf(
            SatelliteListResponseItem(id = 1, name = "Starship-1", active = true),
            SatelliteListResponseItem(id = 3, name = "Dragon-1", active = false)
        )
        val result = responseItems.asExternalSatelliteModel()

        assertEquals(2, result.size)

        assertEquals(
            listOf(
                Satellite(id = 1, name = "Starship-1", active = true),
                Satellite(id = 3, name = "Dragon-1", active = false)
            ), result
        )
    }

    @Test
    fun satellite_detail_response_item_can_be_mapped_to_satellite_detail_entity() {
        val responseItem = SatelliteDetailResponseItem(
            id = 1,
            costPerLaunch = 1000000,
            firstFlight = "2020-01-01",
            height = 100,
            mass = 5000
        )
        val result = responseItem.asEntity()
        assertEquals(1, result.id)
        assertEquals(1000000, result.costPerLaunch)
        assertEquals("2020-01-01", result.firstFlight)
        assertEquals(100, result.height)
        assertEquals(5000, result.mass)
    }

    @Test
    fun satellite_detail_entity_can_be_mapped_to_satellite_detail() {
        val entity = SatelliteDetailEntity(
            id = 1,
            costPerLaunch = 1000000,
            firstFlight = "2020-01-01",
            height = 100,
            mass = 5000
        )
        val result = entity.asExternalModel()
        assertEquals(1, result.id)
        assertEquals(1000000, result.costPerLaunch)
        assertEquals("2020-01-01", result.firstFlight)
        assertEquals(100, result.height)
        assertEquals(5000, result.mass)
    }

    @Test
    fun position_list_can_be_mapped_to_satellite_position() {
        val positions = listOf(
            Position(posX = 0.123, posY = 0.456),
            Position(posX = 0.789, posY = 0.321)
        )
        val result = positions.asExternalModel()
        assertEquals(2, result.size)
        assertEquals(listOf(
            SatellitePosition(posX = 0.123, posY = 0.456),
            SatellitePosition(posX = 0.789, posY = 0.321)
        ), result)
    }
} 