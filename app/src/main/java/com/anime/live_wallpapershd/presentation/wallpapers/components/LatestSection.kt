package com.anime.live_wallpapershd.presentation.wallpapers.components

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
import com.anime.live_wallpapershd.common.Constants
import com.anime.live_wallpapershd.model.Wallpapers
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.home.components.VideoTypeText
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
                WallpapersItems(wallpapers = it){
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






@Composable
fun WallpapersItems(
    wallpapers: Wallpapers,
    onItemClick: (Wallpapers) -> Unit
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
                onItemClick(wallpapers)
            },
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val context = LocalContext.current
            val imageUrl = wallpapers.thumbnail
            Box(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(Constants.ITEM_URL + imageUrl)
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



