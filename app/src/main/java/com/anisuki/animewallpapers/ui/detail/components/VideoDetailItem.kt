package com.anisuki.animewallpapers.ui.detail.components

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.anisuki.animewallpapers.R
import com.anisuki.animewallpapers.common.Constants.ITEM_URL
import com.anisuki.animewallpapers.model.Wallpaper

@Composable
fun VideoDetailItem(
    wallpaper: Wallpaper
)
{

    Box(
        modifier =
    Modifier.fillMaxSize()
    ) {

        val context = LocalContext.current
        val dataUrl = wallpaper.type
        val player = ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(ITEM_URL+ dataUrl))
        }
        val playerView = PlayerView(context)
        val playWhenReady by rememberSaveable {
            mutableStateOf(true)
        }

        playerView.player = player

        LaunchedEffect(player) {
            player.prepare()
            player.playWhenReady = playWhenReady
        }

        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            factory = {
                playerView
            })



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


