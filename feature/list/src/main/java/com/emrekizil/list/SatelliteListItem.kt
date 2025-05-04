package com.emrekizil.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emrekizil.core.model.Satellite
import com.emrekizil.core.ui.R

@Composable
fun SatelliteListItem(
    modifier: Modifier = Modifier,
    satellite: Satellite,
    navigateToDetailScreen:(Int,String)->Unit
) {
    val activityText = if (satellite.active) R.string.feature_list_active else R.string.feature_list_passive
    val activityColor = if (satellite.active) Color.Green else Color.Red
    val activityTextColor = if (satellite.active) Color.Black else Color.LightGray
    Row(
        modifier = modifier.fillMaxWidth().clickable {
            navigateToDetailScreen(satellite.id, satellite.name)
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = activityColor,
                    shape = CircleShape
                )
                .size(16.dp)
        )
        Spacer(
            modifier = Modifier.size(32.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth(0.25f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = satellite.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = activityTextColor
            )
            Text(
                text = stringResource(activityText),
                fontSize = 14.sp,
                color = activityTextColor
            )
        }
    }
}