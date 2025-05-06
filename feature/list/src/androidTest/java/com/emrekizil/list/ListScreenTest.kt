package com.emrekizil.list

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.emrekizil.core.testing.satelliteList
import com.emrekizil.core.ui.theme.SpaceWatchAppTheme
import org.junit.Rule
import org.junit.Test

class ListScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun uiState_whenUiStateLoading_showLoadingState() {
        composeRule.setContent {
            TestListScreen(ListUiState.Loading)
        }
        composeRule.onNodeWithTag("LoadingProgressIndicator").assertIsDisplayed()
    }

    @Test
    fun uiState_whenUiStateError_showErrorView() {
        composeRule.setContent {
            TestListScreen(
                uiState = ListUiState.Error("Exception")
            )
        }
        composeRule.onNodeWithTag("ErrorView").assertIsDisplayed()
    }

    @Test
    fun satellites_whenUiStateSuccess_showUiLoadedAndSatellites() {
        composeRule.setContent {
            TestListScreen(
                uiState = ListUiState.Success(satelliteList())
            )
        }
        composeRule.onNodeWithText("Search").assertIsDisplayed()
        composeRule.onNodeWithText("Starship-1").assertExists()
        composeRule.onNodeWithText("Active").assertExists()
    }

    @Test
    fun emptySatellites_whenUiStateSuccessAndEmptyList_showUiNoResultsFound() {
        composeRule.setContent {
            TestListScreen(
                uiState = ListUiState.Success(emptyList())
            )
        }
        composeRule.onNodeWithText("No results found.").assertExists()
    }


    @Composable
    private fun TestListScreen(uiState: ListUiState) {
        SpaceWatchAppTheme {
            ListScreenContent(
                listUiState = uiState,
                query = "",
                updateQuery = {},
                navigateToDetailScreen = { Int, String -> }
            )
        }
    }
}