package com.anime.live_wallpapershd.presentation.status

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anime.live_wallpapershd.ui.fonts.Fonts

@Composable
fun VideoStatus(videoUrl: String) {
    val text = if (videoUrl.contains(".mp4")) {
        "Live"
    } else {
        null
    }

    if (text != null) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(60.dp)
                .size(30.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            )

        )
        {

            Text(
                text = text,
                color = Color.White,
                fontFamily = Fonts.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )
        }

    }
}
