package com.emrekizil.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emrekizil.core.common.DataSource
import com.emrekizil.core.model.SatelliteDetail
import com.emrekizil.data.repository.SpaceWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val spaceWatchRepository: SpaceWatchRepository
): ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState = _detailUiState.asStateFlow()

    fun getSatelliteDetail(satelliteId:Int){
        viewModelScope.launch {
            spaceWatchRepository.getSatelliteDetail(satelliteId).collect { dataState ->
                when(dataState){
                    is DataSource.Error      -> {
                        _detailUiState.update {
                            DetailUiState.Error(Exception())
                        }
                    }
                    DataSource.Loading       -> {
                        _detailUiState.update {
                            DetailUiState.Loading
                        }
                    }
                    is DataSource.Success<SatelliteDetail> -> {
                        _detailUiState.update {
                            DetailUiState.Success(dataState.data)
                        }
                    }
                }
            }
        }
    }
}


sealed class DetailUiState {
    data object Loading : DetailUiState()
    data class Error(val exception: Exception) : DetailUiState()
    data class Success(val satellites: SatelliteDetail) : DetailUiState()
}