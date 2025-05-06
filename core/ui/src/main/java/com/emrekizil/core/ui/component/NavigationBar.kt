package com.emrekizil.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.emrekizil.core.ui.theme.SpaceWatchTheme
import com.emrekizil.core.ui.util.clickableWithoutRipple

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Icon(
            modifier = Modifier.size(32.dp).clickableWithoutRipple {
                onNavigateBack()
            },
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = SpaceWatchTheme.colors.iconTintColor,
            contentDescription = "Left"
        )
    }
}