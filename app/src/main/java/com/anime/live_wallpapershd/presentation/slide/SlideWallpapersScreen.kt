package com.anime.live_wallpapershd.presentation.slide

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.presentation.slide.component.SlideWallpapersList


@Composable
fun SlideWallpapersScreen(
    navController: NavController,
    wallpapersSlideViewModel : SlideWallpapersViewModel = hiltViewModel()
) {

    Column(modifier = Modifier.fillMaxWidth())
    {
        Spacer(modifier = Modifier.height(35.dp))
        TopBarSlideWallpaper(navController = navController)
        Spacer(modifier = Modifier.height(15.dp))
        SlideWallpapersList(
            wallpapersSlideViewModel = wallpapersSlideViewModel,
            navController = navController
        )
    }
}

@Composable
fun TopBarSlideWallpaper(
    navController: NavController
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)

    ){
        IconButton(
            onClick = {
               navController.navigateUp()
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Back"
            )
        }

    }

}