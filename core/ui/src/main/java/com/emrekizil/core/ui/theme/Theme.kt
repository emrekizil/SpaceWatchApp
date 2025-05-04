package com.emrekizil.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class SpaceWatchColors(
    val backgroundColor: Color = Color.Unspecified,
    val textColor: Color = Color.Unspecified,
    val placeholderColor: Color = Color.Unspecified,
    val textFieldBackgroundColor: Color = Color.Unspecified,
    val textFieldIconTint: Color = Color.Unspecified,
    val textFieldTextColor: Color = Color.Unspecified,
    val unfocusedTextFieldIconTint: Color = Color.Unspecified,
    val passiveTextColor: Color = Color.Unspecified,
    val dividerColor: Color = Color.Unspecified,
    val progressIndicatorColor: Color = Color.Unspecified
)

val LocalCustomColors = staticCompositionLocalOf {
    SpaceWatchColors()
}

@Composable
fun SpaceWatchAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val customColors = if (darkTheme) {
        SpaceWatchColors(
            textColor = Color.White,
            backgroundColor = Color.Black,
            placeholderColor = LightWhite,
            textFieldIconTint = Color.White,
            passiveTextColor = Color.DarkGray,
            dividerColor = Color.LightGray,
            progressIndicatorColor = Color.White,
            textFieldBackgroundColor = Dark,
            unfocusedTextFieldIconTint = LightWhite,
            textFieldTextColor = Color.White
        )
    } else {
        SpaceWatchColors(
            textColor = Color.Black,
            backgroundColor = LightGrey,
            placeholderColor = LightBlack,
            textFieldIconTint = Color.Black,
            passiveTextColor = Color.LightGray,
            dividerColor = Color.Black,
            progressIndicatorColor = Color.Black,
            textFieldBackgroundColor = Color.White,
            unfocusedTextFieldIconTint = LightBlack,
            textFieldTextColor = Color.Black
        )
    }

    CompositionLocalProvider(
        LocalCustomColors provides customColors,
        content = content
    )
}