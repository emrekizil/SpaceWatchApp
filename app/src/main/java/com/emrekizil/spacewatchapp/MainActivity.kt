package com.emrekizil.spacewatchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.emrekizil.data.repository.SpaceWatchRepository
import com.emrekizil.spacewatchapp.navigation.NavHost
import com.emrekizil.spacewatchapp.ui.rememberAppState
import com.emrekizil.spacewatchapp.ui.theme.SpaceWatchAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var repository: SpaceWatchRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState()
            SpaceWatchAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        appState = appState
                    )
                }
            }
        }
    }
}