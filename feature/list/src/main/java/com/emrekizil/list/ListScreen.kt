package com.emrekizil.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.emrekizil.core.ui.R
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreenContent(
    modifier: Modifier = Modifier,
    listUiState: ListUiState,
    query: String,
    updateQuery: (String) -> Unit,
    navigateToDetailScreen: (Int, String) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier
            .background(SpaceWatchTheme.colors.backgroundColor)
            .fillMaxSize()
            .padding(
                horizontal = 24.dp
            )
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            value = query,
            onValueChange = { value ->
                updateQuery(value)
            },
            singleLine = true,
            decorationBox = { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = query,
                    shape = CircleShape,
                    innerTextField = innerTextField,
                    placeholder = {
                        Text(
                            text = stringResource(R.string.feature_list_search_placeholder),
                            color = SpaceWatchTheme.colors.textColor
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(R.string.feature_list_search_placeholder),
                            tint = Color.Black
                        )
                    },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            Icon(
                                modifier = Modifier.clickable {
                                    updateQuery("")
                                },
                                imageVector = Icons.Filled.Close,
                                contentDescription = stringResource(R.string.feature_list_clear_search),
                                tint = Color.Black
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = interactionSource
                )
            }
        )
        when (listUiState) {
            is ListUiState.Error -> {
                Text(
                    text = listUiState.exception.message.orEmpty(),
                    color = Color.Black
                )
            }

            ListUiState.Loading -> {
                CircularProgressIndicator()
            }

            is ListUiState.Success -> {
                if (listUiState.satellites.isEmpty()) {
                    Text(
                        text = stringResource(R.string.feature_list_no_results_found),
                        color = Color.Black
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
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}
