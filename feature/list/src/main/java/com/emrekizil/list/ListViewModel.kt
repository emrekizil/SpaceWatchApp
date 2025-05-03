package com.emrekizil.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emrekizil.core.common.DataSource
import com.emrekizil.core.model.Satellite
import com.emrekizil.data.repository.SpaceWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val spaceWatchRepository: SpaceWatchRepository
) : ViewModel() {

    private val _listUiState = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val listUiState:StateFlow<ListUiState> = _listUiState
        .onStart {
            getSatellites()
        }
        .stateIn(
            scope = viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            initialValue = ListUiState.Loading
        )

    fun getSatellites(
        searchQuery:String = ""
    ){
        viewModelScope.launch {
            spaceWatchRepository.getSatellites(searchQuery).collect { satellites->
                when(satellites){
                    is DataSource.Error -> {
                        _listUiState.update {
                            ListUiState.Error(satellites.exception)
                        }
                    }
                    DataSource.Loading -> {
                        _listUiState.update {
                            ListUiState.Loading
                        }
                    }
                    is DataSource.Success<List<Satellite>> -> {
                        _listUiState.update {
                            ListUiState.Success(satellites.data)
                        }
                    }
                }
            }
        }
    }
}

sealed class ListUiState {
    data object Loading : ListUiState()
    data class Error(val exception: Exception) : ListUiState()
    data class Success(val satellites: List<Satellite>) : ListUiState()
}