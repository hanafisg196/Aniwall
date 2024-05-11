package com.anisuki.animewallpapers.presentation.categories.components

import android.util.Log
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
import com.anisuki.animewallpapers.presentation.categories.WallpapersByCatViewModel
import com.anisuki.animewallpapers.presentation.navgraph.Screen
import com.anisuki.animewallpapers.presentation.wallpapers.components.LoadRefreshItem
import com.anisuki.animewallpapers.presentation.wallpapers.components.LoadingItem
import com.anisuki.animewallpapers.presentation.wallpapers.components.WallpapersItems


@Composable
fun WallpapersByCatList(
    viewModel: WallpapersByCatViewModel,
    navController: NavController
)
{
    val wallpapersByCat = viewModel.wallpapersByCatPager.collectAsLazyPagingItems()

    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .scale(1.01f)
            .padding(horizontal = 16.dp)
    ) {
        items(wallpapersByCat.itemCount) { index ->
            val item = wallpapersByCat[index]
            item?.let {
                WallpapersByCatItem(wallpapers = it){
                    navController.navigate(Screen.WallpaperScreen.route + "/${item.id}")

                }
            }
        }
        when(wallpapersByCat.loadState.append)
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

        when(wallpapersByCat.loadState.refresh)
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