package com.emrekizil.data.service.model


import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: String,
    val positions: List<Position>
)