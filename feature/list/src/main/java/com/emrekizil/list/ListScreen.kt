package com.emrekizil.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel()
) {
    val listUiState by viewModel.listUiState.collectAsStateWithLifecycle()
    ListScreenContent(modifier, listUiState, viewModel::getSatellites)
}

@Composable
fun ListScreenContent(
    modifier: Modifier = Modifier,
    listUiState: ListUiState,
    getSatellites: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 24.dp
            )
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { value ->
                text = value
                getSatellites(value)
            },
            singleLine = true,
            placeholder = {
                Text("Search")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
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
                if (listUiState.satellites.isEmpty()){
                    Text("nothin to show", color = Color.Black)
                } else {
                    LazyColumn {
                        items(
                            items = listUiState.satellites,
                            key = { it.id }
                        ) {
                            SatelliteListItem(
                                satellite = it
                            )
                        }
                    }
                }
            }
        }
    }

}
