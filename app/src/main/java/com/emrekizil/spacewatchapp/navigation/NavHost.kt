package com.emrekizil.spacewatchapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.emrekizil.detail.navigation.detailScreen
import com.emrekizil.detail.navigation.navigateToDetailScreen
import com.emrekizil.list.navigation.ListRoute
import com.emrekizil.list.navigation.listScreen
import com.emrekizil.spacewatchapp.ui.AppState

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    appState: AppState
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = ListRoute,
        modifier = modifier
    ){
        listScreen(
            navController::navigateToDetailScreen
        )
        detailScreen()
    }
}