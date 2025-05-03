package com.emrekizil.data.service.model

import kotlinx.serialization.Serializable

typealias SatelliteListResponse = List<SatelliteListResponseItem>

@Serializable
data class SatelliteListResponseItem(
    val active: Boolean,
    val id: Int,
    val name: String
)