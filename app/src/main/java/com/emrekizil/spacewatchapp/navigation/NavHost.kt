package com.emrekizil.spacewatchapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.emrekizil.detail.navigation.detailScreen
import com.emrekizil.detail.navigation.navigateToDetailScreen
import com.emrekizil.list.navigation.ListRoute
import com.emrekizil.list.navigation.listScreen

@Composable
fun NavHost(
    modifier: Modifier = Modifier
) {
    val navController : NavHostController = rememberNavController()
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