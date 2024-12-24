package com.anime.live_wallpapershd.presentation.wallpapers.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.ads.InterstitialAd
import com.anime.live_wallpapershd.presentation.ads.MediumNativeAd
import com.anime.live_wallpapershd.presentation.wallpapers.WallpapersViewModel


@Composable
fun LatestSection(
    viewModel: WallpapersViewModel,
    navController: NavController
) {
    val interstitialAd: InterstitialAd = viewModel()
    val wallpapersList = viewModel.wallpaperPager.collectAsLazyPagingItems()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        interstitialAd.loadAd(context)
    }
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(vertical = 5.dp, horizontal = 5.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .scale(1.01f)
            .padding(horizontal = 16.dp)
            .padding(bottom = 65.dp)
    ) {
        val totalItems = wallpapersList.itemCount
        items(totalItems + totalItems / 4, span = { index ->
            // Iklan memenuhi 2 kolom, wallpaper hanya 1 kolom
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
                val item = wallpapersList[adjustedIndex]
                item?.let {
                    WallpaperListItem(wallpapers = it) {
                        interstitialAd.onClickShowAd(context)
                        navController.navigate(Screen.WallpaperScreen.route + "/${item.id}")
                    }
                }
            }
        }
        when(wallpapersList.loadState.append)
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

        when(wallpapersList.loadState.refresh)
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

    MediumNativeAd(context = context, nativeId = "ca-app-pub-3940256099942544/2247696110" )

}








