package com.anisuki.animewallpapers.ui.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.anisuki.animewallpapers.model.ButtonMenuContent

@Composable
fun ButtonMenuItem(
    item:ButtonMenuContent,
    onItemClick: () -> Unit
)
{
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier =
        Modifier.clickable {
            onItemClick.invoke()
        }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .padding(10.dp)
        )
        {
            Icon(
                painter = painterResource(id = item.iconId),
                contentDescription = null,
                modifier =
                Modifier.size(25.dp),
                Color.Gray

            )
        }

    }
}