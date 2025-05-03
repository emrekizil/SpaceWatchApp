package com.emrekizil.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.emrekizil.core.model.SatellitePosition

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    satelliteId: Int,
    satelliteTitle: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val detailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()
    val position by viewModel.position.collectAsStateWithLifecycle()
    LaunchedEffect(true) {
        viewModel.getSatelliteDetail(satelliteId)
    }
    DetailScreenContent(detailUiState = detailUiState, satelliteTitle = satelliteTitle, position = position)
}

@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,
    satelliteTitle: String,
    position: SatellitePosition
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (detailUiState) {
            is DetailUiState.Error   -> {
                Text(text = "something happened")
            }

            DetailUiState.Loading    -> {
                CircularProgressIndicator()
            }

            is DetailUiState.Success -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = satelliteTitle,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        text = detailUiState.satellites.firstFlight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "${detailUiState.satellites.height}/${detailUiState.satellites.mass}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = detailUiState.satellites.costPerLaunch,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "(${position.posX},${position.posY})",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}
