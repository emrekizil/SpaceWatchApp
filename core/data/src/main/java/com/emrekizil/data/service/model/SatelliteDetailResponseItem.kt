package com.emrekizil.data.service.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias SatelliteDetailResponse = List<SatelliteDetailResponseItem>

@Serializable
data class SatelliteDetailResponseItem(
    val id: Int,
    @SerialName("cost_per_launch")
    val costPerLaunch: Int,
    @SerialName("first_flight")
    val firstFlight: String,
    val height: Int,
    val mass: Int
)