package com.anime.live_wallpapershd.presentation.slide.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.ads.MediumNativeAd
import com.anime.live_wallpapershd.presentation.slide.SlideWallpapersViewModel
import com.anime.live_wallpapershd.presentation.wallpapers.components.LoadRefreshItem
import com.anime.live_wallpapershd.presentation.wallpapers.components.LoadingItem
import com.anime.live_wallpapershd.presentation.wallpapers.components.WallpaperListItem


@Composable
fun SlideWallpapersList(
    wallpapersSlideViewModel : SlideWallpapersViewModel,
    navController: NavController
){
    val wallpapersSlide = wallpapersSlideViewModel.wallpapersSlidePager.collectAsLazyPagingItems()
    val context = LocalContext.current
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .scale(1.01f)
            .padding(horizontal = 16.dp)
            .padding(bottom = 70.dp)
    ) {
        val totalItems = wallpapersSlide.itemCount
        items(totalItems + totalItems / 4, span = { index ->
            if ((index + 1) % 5 == 0) GridItemSpan(2) else GridItemSpan(1)
        }) { index ->
            val isAdPosition = (index + 1) % 5 == 0

            if (isAdPosition) {
                MediumNativeAd(
                    context = context,
                    nativeId = "ca-app-pub-3940256099942544/2247696110",

                    )
            } else {
                val adjustedIndex = index - (index / 5)
                val item = wallpapersSlide[adjustedIndex]
                item?.let {
                    WallpaperListItem(wallpapers = it) {
                        navController.navigate(Screen.WallpaperScreen.route + "/${item.id}")
                    }
                }
            }
        }
        when(wallpapersSlide.loadState.append)
        {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }

            is LoadState.Error ->
                item {
                    // TODO
                }
        }

        when(wallpapersSlide.loadState.refresh)
        {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
                    LoadRefreshItem()

                }
            }

            is LoadState.Error ->
                item {
                    // TODO
                }
        }

    }
}