package com.anime.live_wallpapershd.presentation.wallpapers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anime.live_wallpapershd.model.Tabs
import com.anime.live_wallpapershd.presentation.ads.BannerAd
import com.anime.live_wallpapershd.presentation.ads.MediumNativeAd
import com.anime.live_wallpapershd.presentation.wallpapers.components.LatestSection
import com.anime.live_wallpapershd.presentation.wallpapers.components.PopularSection
import com.anime.live_wallpapershd.presentation.wallpapers.components.WallpapersTabs


@Composable
fun WallpapersScreen(
    viewModelLatest: WallpapersViewModel = hiltViewModel(),
    viewModelPopular: PopularViewModel = hiltViewModel(),
    navController: NavController
)
{

    var selectedTabIndex by remember{
        mutableIntStateOf(0)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(35.dp))
            WallpapersTabs(
                tabsModel = listOf(
                    Tabs(title = "Latest"),
                    Tabs(title = "Popular"),
                ),
                onTabSelected = { selectedTabIndex = it },
                navController = navController
            )
            Spacer(modifier = Modifier.height(10.dp))
            when (selectedTabIndex) {
                0 -> LatestSection(viewModel = viewModelLatest,navController)
                1 -> PopularSection(viewModel = viewModelPopular,navController)
            }



        }
       BannerAd(modifier = Modifier
           .align(Alignment.BottomCenter)
           .padding(bottom = 2.dp))
    }





}


