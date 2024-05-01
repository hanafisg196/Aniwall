package com.anisuki.animewallpapers.ui.detail.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.anisuki.animewallpapers.R
import com.anisuki.animewallpapers.common.Constants.ITEM_URL
import com.anisuki.animewallpapers.model.Wallpaper

@Composable
fun WallpaperDetailItem(
    wallpaper: Wallpaper
)
{

    Box(modifier = Modifier) {
        val context = LocalContext.current
        val dataUrl = wallpaper.type
        Log.d("kontol", "Trying to load thumbnail from URL: $dataUrl")
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(ITEM_URL + dataUrl)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale
                         .FillBounds,
            )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 25.dp, end = 25.dp)
                .align(Alignment.TopStart)
        ) {
            Box(modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
            ){
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = null,
                    modifier =
                    Modifier
                        .size(15.dp)
                        .align(Alignment.Center),
                    Color.Black,

                    )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)

            ){
                Icon(
                    painter = painterResource(R.drawable.heart),
                    contentDescription = null,
                    modifier =
                    Modifier
                        .size(20.dp)
                        .align(Alignment.Center),
                    Color.Gray,
                )
            }

        }
        BottomSheet(wallpaper)
    }

}