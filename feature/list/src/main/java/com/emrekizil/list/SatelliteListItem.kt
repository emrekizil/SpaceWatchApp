package com.emrekizil.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emrekizil.core.model.Satellite

@Composable
fun SatelliteListItem(
    modifier: Modifier = Modifier,
    satellite: Satellite,
    navigateToDetailScreen:(Int,String)->Unit
) {
    val activityText = if (satellite.active) "Active" else "Passive"
    val activityColor = if (satellite.active) Color.Green else Color.Red
    Row(
        modifier = modifier.fillMaxWidth().clickable {
            navigateToDetailScreen(satellite.id,satellite.name)
        },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = activityColor,
                    shape = CircleShape
                ).size(16.dp)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = satellite.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                text = activityText,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}