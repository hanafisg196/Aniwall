package com.anime.live_wallpapershd.presentation.detail


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anime.live_wallpapershd.presentation.detail.components.VideoDetailItem
import com.anime.live_wallpapershd.presentation.detail.components.WallpaperDetailItem


@Composable
fun WallpaperScreen(
    wallpaperId: Int,
    wallpaperViewModel: WallpaperViewModel = hiltViewModel(),
    navController: NavController
) {
    val wallpaperState by wallpaperViewModel.state.collectAsState()

    LaunchedEffect(wallpaperId) {
        wallpaperViewModel.getWallpaper(wallpaperId)
    }

    Column(

        modifier =
        Modifier.fillMaxSize()
    ) {
        wallpaperState?.let { wallpaper ->
           val videoUrl = wallpaper.type
            if (videoUrl.contains(".mp4"))
            {
                VideoDetailItem(wallpaper,navController)
            }else {
                WallpaperDetailItem(wallpaper,navController)
            }
        }
    }


}










