package com.anisuki.animewallpapers.presentation.categories.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.anisuki.animewallpapers.common.Constants
import com.anisuki.animewallpapers.model.Wallpapers
import com.anisuki.animewallpapers.presentation.home.components.VideoTypeText


@Composable
fun WallpapersByCatItem(
    wallpapers: Wallpapers,
    onItemClick: (Wallpapers) -> Unit
)
{

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .width(150.dp)
            .height(300.dp)
            .padding(vertical = 8.dp)
            .clickable {
                onItemClick(wallpapers)
            },
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val context = LocalContext.current
            val imageUrl = wallpapers.thumbnail
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(Constants.ITEM_URL + imageUrl)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.TopStart)
                ) {

//                    VideoTypeText(videoUrl = wallpapers.type)

                }
            }
        }
    }

}