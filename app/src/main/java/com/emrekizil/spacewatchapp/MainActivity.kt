package com.emrekizil.spacewatchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.emrekizil.core.ui.theme.SpaceWatchAppTheme
import com.emrekizil.core.ui.theme.SpaceWatchTheme
import com.emrekizil.spacewatchapp.navigation.NavHost
import com.emrekizil.spacewatchapp.ui.rememberAppState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState()
            SpaceWatchAppTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    containerColor = SpaceWatchTheme.colors.backgroundColor
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        appState = appState
                    )
                }
            }
        }
    }
}