package com.emrekizil.core.model


import com.google.gson.annotations.SerializedName

typealias SatelliteListResponse = List<SatelliteListResponseItem>

data class SatelliteListResponseItem(
    @SerializedName("active")
    val active: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)