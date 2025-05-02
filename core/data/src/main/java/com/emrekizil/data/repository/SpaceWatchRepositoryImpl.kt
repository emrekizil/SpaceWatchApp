package com.emrekizil.data.repository

import android.content.Context
import com.emrekizil.core.common.DataSource
import com.emrekizil.core.model.SatelliteListResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SpaceWatchRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SpaceWatchRepository {
    override fun getSatellites(): Flow<DataSource<SatelliteListResponse>> = flow {
        emit(DataSource.Loading)
        try {
            val jsonString = context.readJsonFromAssets("satellite-list.json")
            val gson = Gson()
            val type = object : TypeToken<SatelliteListResponse>(){}.type
            val satelliteList:SatelliteListResponse = gson.fromJson(jsonString,type)
            emit(DataSource.Success(satelliteList))
        } catch (e:Exception) {
            emit(DataSource.Error(e))
        }
    }.flowOn(Dispatchers.IO)
}

fun Context.readJsonFromAssets(fileName: String): String {
    return assets.open(fileName).bufferedReader().use { it.readText() }
}