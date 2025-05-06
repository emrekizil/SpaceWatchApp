package com.emrekizil.list

import com.emrekizil.core.model.Satellite

sealed class ListUiState {
    data object Loading : ListUiState()
    data class Error(val message: String?) : ListUiState()
    data class Success(val satellites: List<Satellite>) : ListUiState()
}