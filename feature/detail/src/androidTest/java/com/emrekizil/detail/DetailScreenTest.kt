package com.emrekizil.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.emrekizil.core.ui.theme.SpaceWatchAppTheme
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun uiState_whenUiStateLoading_showLoadingState() {
        composeRule.setContent {
            TestDetailScreen(DetailUiState.Loading)
        }
        composeRule.onNodeWithTag("LoadingProgressIndicator").assertIsDisplayed()
    }

    @Test
    fun uiState_whenUiStateError_showErrorView() {
        composeRule.setContent {
            TestDetailScreen(
                detailUiState = DetailUiState.Error("Exception")
            )
        }
        composeRule.onNodeWithTag("ErrorView").assertIsDisplayed()
    }

    @Test
    fun uiState_whenUiStateSuccess_showUiLoadedAndSatellites() {
        composeRule.setContent {
            TestDetailScreen(
                detailUiState = DetailUiState.Success(satelliteUiModel())
            )
        }
        composeRule.onNodeWithText("Starship-1").assertExists()
        composeRule.onNodeWithText("2025-01-01").assertExists()
    }

    @Composable
    private fun TestDetailScreen(
        detailUiState: DetailUiState,
        satelliteTitle: String = "Starship-1"
    ) {
        SpaceWatchAppTheme {
            DetailScreenContent(
                detailUiState = detailUiState,
                satelliteTitle = satelliteTitle,
                positionContent = { TestPositionContent() },
                navigateBack = {}
            )
        }
    }

    @Composable
    private fun TestPositionContent() {}

    private fun satelliteUiModel(): SatelliteUiModel = SatelliteUiModel(
        costPerLaunch = "7200",
        firstFlight = "2025-01-01",
        height = 700,
        id = 1,
        mass = 500
    )
}