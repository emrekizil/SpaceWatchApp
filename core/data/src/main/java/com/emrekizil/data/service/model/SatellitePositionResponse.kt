package com.emrekizil.data.service.model


import kotlinx.serialization.Serializable

@Serializable
data class SatellitePositionResponse(
    val list: List<Item>
)