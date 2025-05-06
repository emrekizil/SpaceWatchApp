package com.emrekizil.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.emrekizil.core.ui.theme.SpaceWatchTheme

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().testTag("LoadingProgressIndicator"),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator(
            color = SpaceWatchTheme.colors.progressIndicatorColor
        )
    }
}