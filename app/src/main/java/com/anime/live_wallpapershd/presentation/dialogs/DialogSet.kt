package com.anime.live_wallpapershd.presentation.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.anime.live_wallpapershd.common.Constants
import com.anime.live_wallpapershd.model.Wallpaper
import com.anime.live_wallpapershd.helper.bothWallpaperSet
import com.anime.live_wallpapershd.helper.homeWallpaperSet
import com.anime.live_wallpapershd.helper.lockWallpaperSet


@Composable
fun DialogSet(
    wallpaper: Wallpaper,
    onDismiss: () -> Unit
)
{
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val contentUrl =  Constants.ITEM_URL + wallpaper.type


    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { /*TODO*/ },
        modifier = Modifier.height(350.dp),
        title = {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(imageVector = Icons.Default.Lock, contentDescription =  "Wallpaper Set")
                }
        },

        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),

            ) {
                    Button(
                        onClick = {
                            bothWallpaperSet(
                            contentUrl = contentUrl,
                            context = context,
                            scope = scope
                        ) },
                        modifier = Modifier
                            .width(180.dp)
                            .padding(10.dp)
                        ) {
                        Text(text = "Both Screen")

                    }

                Button(
                    onClick = {
                        homeWallpaperSet(
                            contentUrl = contentUrl,
                            context = context,
                            scope = scope
                        )
                    },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(10.dp)
                ) {
                    Text(text = "Home Screen")

                }

                Button(
                    onClick = {
                        lockWallpaperSet(
                            contentUrl = contentUrl,
                            context = context,
                            scope = scope
                        )
                    },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(10.dp)
                ) {
                    Text(text = "Lock Screen")

                }
            }
        }
    )

}