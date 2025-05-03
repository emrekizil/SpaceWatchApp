package com.emrekizil.data.service.model


import kotlinx.serialization.Serializable

@Serializable
data class Position(
    val posX: Double,
    val posY: Double
)