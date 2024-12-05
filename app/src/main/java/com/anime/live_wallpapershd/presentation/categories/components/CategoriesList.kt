package com.anime.live_wallpapershd.presentation.categories.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.categories.CategoriesVieModel
import com.anime.live_wallpapershd.presentation.wallpapers.components.LoadRefreshItem
import com.anime.live_wallpapershd.presentation.wallpapers.components.LoadingItem


@Composable
fun CategoriesList(
    viewmodel : CategoriesVieModel,
    navController: NavController
)
{
    val categoriesList = viewmodel.categoriesPager.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = categoriesList.itemCount,
            key = { index -> categoriesList[index]?.id!! }
        ) { index ->
            val item = categoriesList[index]
            if (item != null) {
                CategoriesItem(
                    categories = item,
                    onItemClick = {
                        navController.navigate(Screen.WallpapersByCatScreen.route + "/${item.id}")
                        Log.d("Categories to wallpapers", "Item id: ${item.id}")
                    }
                )
            }
        }
        when(categoriesList.loadState.append){
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

        when(categoriesList.loadState.refresh)
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