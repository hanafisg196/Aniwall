package com.anime.live_wallpapershd.presentation.wallpapers.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.wallpapers.WallpapersViewModel


@Composable
fun LatestSection(
    viewModel: WallpapersViewModel,
    navController: NavController
) {
    val wallpapersList = viewModel.wallpaperPager.collectAsLazyPagingItems()

    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .scale(1.01f)
            .padding(horizontal = 16.dp)
    ) {
        items(wallpapersList.itemCount) { index ->
            val item = wallpapersList[index]
            item?.let {
                WallpaperListItem(wallpapers = it){
                    navController.navigate(Screen.WallpaperScreen.route + "/${item.id}")
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
}








