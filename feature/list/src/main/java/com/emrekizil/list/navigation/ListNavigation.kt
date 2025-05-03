package com.emrekizil.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.emrekizil.list.ListScreen
import kotlinx.serialization.Serializable

@Serializable
data object ListRoute

fun NavController.navigateToListScreen() =
    navigate(route = ListRoute)

fun NavGraphBuilder.listScreen(
    navigateToDetailScreen: (Int,String) -> Unit
) {
    composable<ListRoute> {
        ListScreen(navigateToDetailScreen = navigateToDetailScreen)
    }
}