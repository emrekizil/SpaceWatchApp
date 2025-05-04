package com.emrekizil.data.service

import com.emrekizil.core.common.Dispatcher
import com.emrekizil.core.common.SwDispatchers
import com.emrekizil.data.service.model.Position
import com.emrekizil.data.service.model.SatelliteDetailResponse
import com.emrekizil.data.service.model.SatelliteDetailResponseItem
import com.emrekizil.data.service.model.SatelliteListResponse
import com.emrekizil.data.service.model.SatellitePositionResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import javax.inject.Inject

class SatelliteService @Inject constructor(
    @Dispatcher(SwDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val serviceJson: Json,
    private val assets: SwAssetManager
) {
    suspend fun getSatellites(searchQuery:String): SatelliteListResponse {
        val data = getDataFromJsonFile<SatelliteListResponse>(LIST_ASSET).filter {
            it.name.lowercase().contains(searchQuery)
        }
        return data
    }


    suspend fun getSatelliteDetail(
        satelliteId: Int
    ): SatelliteDetailResponseItem {
        val data = getDataFromJsonFile<SatelliteDetailResponse>(DETAIL_ASSET).first {
            it.id == satelliteId
        }
        return data
    }

    suspend fun getSatellitePosition(
        satelliteId: Int
    ): List<Position> {
        val data = getDataFromJsonFile<SatellitePositionResponse>(POSITIONS_ASSET).list.first {
            it.id == "$satelliteId"
        }
        return data.positions
    }


    private suspend inline fun <reified T> getDataFromJsonFile(fileName: String): T =
        withContext(ioDispatcher) {
            assets.open(fileName).use { inputStream ->
                inputStream.bufferedReader().use(BufferedReader::readText)
                    .let(serviceJson::decodeFromString)
            }
        }

    companion object {
        private const val LIST_ASSET = "satellite-list.json"
        private const val DETAIL_ASSET = "satellite-detail.json"
        private const val POSITIONS_ASSET = "positions.json"
    }
}