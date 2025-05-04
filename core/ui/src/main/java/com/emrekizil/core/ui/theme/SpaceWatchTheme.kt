package com.emrekizil.core.ui.theme

import androidx.compose.runtime.Composable

object SpaceWatchTheme {
    val colors : SpaceWatchColors
        @Composable
        get() = LocalCustomColors.current
}