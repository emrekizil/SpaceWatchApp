package com.emrekizil.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    satelliteId: Int,
    satelliteTitle: String,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val detailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()
    LaunchedEffect(true) {
        viewModel.getSatelliteDetail(satelliteId)
    }
    DetailScreenContent(detailUiState = detailUiState, satelliteTitle = satelliteTitle)
}

@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,
    satelliteTitle: String
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
                Text(
                    satelliteTitle,
                    color = Color.Black
                )
            }
        }
    }
}
