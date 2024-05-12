package com.anisuki.animewallpapers.presentation.categories.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.anisuki.animewallpapers.common.Constants
import com.anisuki.animewallpapers.model.Wallpapers
import com.anisuki.animewallpapers.presentation.categories.WallpapersByCatViewModel
import com.anisuki.animewallpapers.presentation.home.components.VideoTypeText
import com.anisuki.animewallpapers.presentation.navgraph.Screen
import com.anisuki.animewallpapers.presentation.wallpapers.components.LoadRefreshItem
import com.anisuki.animewallpapers.presentation.wallpapers.components.LoadingItem


@Composable
fun WallpapersByCatList(
    viewModel: WallpapersByCatViewModel,
    navController: NavController
) {
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
                ItemsWallpapersByCat(wallpapers = it) {
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

@Composable
fun  ItemsWallpapersByCat(
   wallpapers: Wallpapers,
   onclickItem : (Wallpapers) -> Unit

) {
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
                onclickItem(wallpapers)
            }

    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val context = LocalContext.current
            val imgUrl = wallpapers.thumbnail
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(Constants.ITEM_URL + imgUrl)
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

                    VideoTypeText(videoUrl = wallpapers.type)

                }
            }
        }
    }
}
