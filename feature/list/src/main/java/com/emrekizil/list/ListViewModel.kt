package com.emrekizil.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emrekizil.core.common.DataSource
import com.emrekizil.core.model.Satellite
import com.emrekizil.data.repository.SpaceWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
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
    val listUiState: StateFlow<ListUiState> = _listUiState
        .onStart {
            getSatellites()
            observeQuery()
        }
        .stateIn(
            scope = viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            initialValue = ListUiState.Loading
        )

    var query by mutableStateOf("")
        private set

    private fun getSatellites(
        searchQuery: String = ""
    ) {
        viewModelScope.launch {
            spaceWatchRepository.getSatellites(searchQuery).collect { satellites ->
                when (satellites) {
                    is DataSource.Error -> {
                        _listUiState.update {
                            ListUiState.Error(satellites.exception.message)
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

    fun updateQuery(newQuery: String) {
        query = newQuery
    }

    private fun observeQuery() {
        viewModelScope.launch {
            snapshotFlow { query }
                .distinctUntilChanged()
                .debounce(TIMEOUT)
                .collect { newQuery ->
                    when {
                        newQuery.isEmpty() -> getSatellites(newQuery)
                        newQuery.length > MINIMUM_LENGTH -> getSatellites(newQuery)
                    }
                }
        }
    }

    companion object{
        const val TIMEOUT = 600L
        const val MINIMUM_LENGTH = 1
    }
}
