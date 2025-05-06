package com.emrekizil.detail

import com.emrekizil.core.model.SatelliteDetail
import com.emrekizil.core.ui.formatDateString
import com.emrekizil.core.ui.formatNumber

sealed class DetailUiState {
    data object Loading : DetailUiState()
    data class Error(val message: String?) : DetailUiState()
    data class Success(val satellites: SatelliteUiModel) : DetailUiState()
}

data class SatelliteUiModel (
    val costPerLaunch: String,
    val firstFlight: String,
    val height: Int,
    val id: Int,
    val mass: Int
)

fun SatelliteDetail.toSatelliteUiModel():SatelliteUiModel =
    SatelliteUiModel(
        costPerLaunch = formatNumber(this.costPerLaunch),
        firstFlight = formatDateString(this.firstFlight),
        height = this.height,
        id = this.id,
        mass = this.mass
    )