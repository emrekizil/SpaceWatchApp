package com.emrekizil.detail

import android.os.SystemClock
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emrekizil.core.common.DataSource
import com.emrekizil.core.model.SatelliteDetail
import com.emrekizil.core.model.SatellitePosition
import com.emrekizil.data.repository.SpaceWatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.AbstractLongTimeSource
import kotlin.time.DurationUnit
import kotlin.time.TimeMark
import kotlin.time.TimeSource
import kotlin.time.toDuration

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val spaceWatchRepository: SpaceWatchRepository
): ViewModel() {
    private val _detailUiState = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val detailUiState = _detailUiState.asStateFlow()

    private val _position = MutableStateFlow(SatellitePosition(0.0,0.0))
    val position = _position.asStateFlow()

    fun getSatelliteDetail(satelliteId:Int){
        viewModelScope.launch {
            spaceWatchRepository.getSatelliteDetail(satelliteId).collect { dataState ->
                when(dataState){
                    is DataSource.Error      -> {
                        _detailUiState.update {
                            DetailUiState.Error(dataState.exception.message)
                        }
                    }
                    DataSource.Loading       -> {
                        _detailUiState.update {
                            DetailUiState.Loading
                        }
                    }
                    is DataSource.Success<SatelliteDetail> -> {
                        _detailUiState.update {
                            DetailUiState.Success(dataState.data.toSatelliteUiModel())
                        }
                    }
                }
            }

        }
    }

    fun getSatellitePosition(satelliteId: Int){
        viewModelScope.launch {
            spaceWatchRepository.getSatellitePosition(satelliteId).collect { dataState ->
                when(dataState){
                    is DataSource.Success<List<SatellitePosition>> -> {
                        randomPositionFlow(dataState.data).collect { randomPosition ->
                            _position.update {
                                randomPosition
                            }
                        }
                    }
                    else -> {}
                }
            }
        }

    }

    private fun randomPositionFlow(data: List<SatellitePosition>) : Flow<SatellitePosition>{
        return synchronizedTickerFlow(period = 3000L).map { data.random() }
    }
}

fun synchronizedTickerFlow(
    period: Long,
    timeSource: TimeSource = ElapsedRealTimeSource
): Flow<Unit> {
    return flow {
        var nextEmissionTimeMark: TimeMark? = null
        flow {
            nextEmissionTimeMark?.let { delay(-it.elapsedNow()) }
            while (true) {
                emit(Unit)
                nextEmissionTimeMark = timeSource.markNow() + period.toDuration(DurationUnit.NANOSECONDS)
                delay(period)
            }
        }.collect(this)
    }
}

object ElapsedRealTimeSource : AbstractLongTimeSource(DurationUnit.NANOSECONDS) {
    override fun read(): Long = SystemClock.elapsedRealtimeNanos()
    override fun toString(): String = "TimeSource(SystemClock.elapsedRealtimeNanos())"
}