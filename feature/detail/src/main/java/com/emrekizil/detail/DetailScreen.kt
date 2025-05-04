package com.emrekizil.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.emrekizil.core.model.SatellitePosition
import com.emrekizil.core.ui.R
import com.emrekizil.core.ui.theme.SpaceWatchTheme

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
    DetailScreenContent(
        detailUiState = detailUiState,
        satelliteTitle = satelliteTitle,
        positionContent = { PositionDisplay(position = position) }
    )
}

@Composable
fun DetailScreenContent(
    modifier: Modifier = Modifier,
    detailUiState: DetailUiState,
    satelliteTitle: String,
    positionContent: @Composable () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (detailUiState) {
            is DetailUiState.Error -> {
                Text(text = "something happened")
            }

            DetailUiState.Loading -> {
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
                        color = SpaceWatchTheme.colors.textColor
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = detailUiState.satellites.firstFlight,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = SpaceWatchTheme.colors.placeholderColor
                    )
                    Spacer(Modifier.height(48.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(R.string.feature_detail_height_mass))
                            }
                            append("${detailUiState.satellites.height}/${detailUiState.satellites.mass}")
                        },
                        fontSize = 12.sp,
                        color = SpaceWatchTheme.colors.textColor
                    )
                    Spacer(Modifier.height(24.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(R.string.feature_detail_cost))
                            }
                            append(detailUiState.satellites.costPerLaunch)
                        },
                        fontSize = 12.sp,
                        color = SpaceWatchTheme.colors.textColor
                    )
                    Spacer(Modifier.height(24.dp))
                    positionContent()
                }
            }
        }
    }
}

@Composable
fun PositionDisplay(position: SatellitePosition) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(stringResource(R.string.feature_detail_last_position))
            }
            append(
                stringResource(
                    R.string.feature_detail_last_position_value,
                    position.posX, position.posY
                )
            )
        },
        fontSize = 12.sp,
        color = SpaceWatchTheme.colors.textColor
    )
}
