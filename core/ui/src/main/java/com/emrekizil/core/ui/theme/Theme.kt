package com.emrekizil.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class SpaceWatchColors(
    val backgroundColor:Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified
)

val LocalCustomColors = staticCompositionLocalOf {
    SpaceWatchColors()
}


@Composable
fun SpaceWatchAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val customColors = if (darkTheme){
        SpaceWatchColors(
            textColor = Color.White,
            backgroundColor = Color.Black
        )
    } else {
        SpaceWatchColors(
            textColor = Color.Black,
            backgroundColor = LightGrey
        )
    }

    CompositionLocalProvider(
        LocalCustomColors provides customColors,
        content = content
    )
}