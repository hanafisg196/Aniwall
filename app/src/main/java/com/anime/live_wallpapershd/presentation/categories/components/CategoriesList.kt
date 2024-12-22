package com.anime.live_wallpapershd.presentation.categories.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.ads.MediumNativeAd
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
//    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 70.dp)
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
//                if (index > 0 && index % 2 == 0) {
//                    MediumNativeAd(
//                        context = context,
//                        nativeId = "ca-app-pub-3940256099942544/2247696110",
//                    )
//                }

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