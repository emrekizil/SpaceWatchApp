package com.emrekizil.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.emrekizil.detail.DetailScreen
import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(
    val satelliteId: Int,
    val satelliteTitle: String
)

fun NavController.navigateToDetailScreen(
    satelliteId: Int,
    satelliteTitle:String
) = navigate(
        route = DetailRoute(
            satelliteId,
            satelliteTitle
        )
    )

fun NavGraphBuilder.detailScreen() {
    composable<DetailRoute> {
        val argument = it.toRoute<DetailRoute>()
        DetailScreen(
            satelliteId = argument.satelliteId,
            satelliteTitle = argument.satelliteTitle
        )
    }
}