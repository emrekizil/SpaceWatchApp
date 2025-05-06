package com.emrekizil.core.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emrekizil.core.ui.theme.SpaceWatchAppTheme

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    errorText: String? = null
) {
    Column(
        modifier = modifier.fillMaxSize().testTag("ErrorView"),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            imageVector = Icons.Filled.Close,
            contentDescription = "Error",
            tint = Color.Red
        )
        Spacer(Modifier.height(16.dp))
        errorText?.let {
            Text(
                text = errorText,
                color = Color.Red,
                fontSize = 18.sp
            )
        }

    }
}

@PreviewLightDark
@Composable
fun PreviewErrorView(modifier: Modifier = Modifier) {
    SpaceWatchAppTheme {
        ErrorView(
            errorText = "Something Happened"
        )
    }
}