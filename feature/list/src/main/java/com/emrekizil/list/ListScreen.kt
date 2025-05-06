package com.emrekizil.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.emrekizil.core.model.Satellite
import com.emrekizil.core.ui.R
import com.emrekizil.core.ui.component.ErrorView
import com.emrekizil.core.ui.component.LoadingView
import com.emrekizil.core.ui.component.SearchBar
import com.emrekizil.core.ui.theme.SpaceWatchAppTheme
import com.emrekizil.core.ui.theme.SpaceWatchTheme

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel(),
    navigateToDetailScreen: (Int, String) -> Unit
) {
    val listUiState by viewModel.listUiState.collectAsStateWithLifecycle()
    ListScreenContent(
        modifier,
        listUiState,
        viewModel.query,
        viewModel::updateQuery,
        navigateToDetailScreen
    )
}

@Composable
fun ListScreenContent(
    modifier: Modifier = Modifier,
    listUiState: ListUiState,
    query: String,
    updateQuery: (String) -> Unit,
    navigateToDetailScreen: (Int, String) -> Unit
) {
    Column(
        modifier = modifier
            .background(SpaceWatchTheme.colors.backgroundColor)
            .fillMaxSize()
            .padding(
                horizontal = 24.dp
            )
    ) {
        SearchBar(
            modifier = Modifier.padding(bottom = 24.dp),
            text = query,
            updateValue = updateQuery,
            placeholderText = R.string.feature_list_search_placeholder
        )
        when (listUiState) {
            is ListUiState.Error -> {
                ErrorView(
                    errorText = listUiState.message
                )
            }

            ListUiState.Loading -> {
                LoadingView()
            }

            is ListUiState.Success -> {
                if (listUiState.satellites.isEmpty()) {
                    Spacer(Modifier.height(36.dp))
                    Text(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        text = stringResource(R.string.feature_list_no_results_found),
                        color = SpaceWatchTheme.colors.textColor
                    )
                } else {
                    LazyColumn {
                        items(
                            items = listUiState.satellites,
                            key = { it.id }
                        ) { satellite ->
                            SatelliteListItem(
                                satellite = satellite,
                                navigateToDetailScreen = navigateToDetailScreen
                            )
                            if (satellite != listUiState.satellites.last()) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(vertical = 8.dp),
                                    color = SpaceWatchTheme.colors.dividerColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ListScreenContentPreview() {
    SpaceWatchAppTheme {
        ListScreenContent(
            listUiState = ListUiState.Success(
                satellites = listOf(
                    Satellite(
                        id = 1,
                        name = "Starlink-1",
                        active = true
                    ),
                    Satellite(
                        id = 2,
                        name = "Starlink-2",
                        active = false
                    )
                )
            ),
            query = "",
            updateQuery = {},
            navigateToDetailScreen = { _, _ -> }
        )
    }
}
