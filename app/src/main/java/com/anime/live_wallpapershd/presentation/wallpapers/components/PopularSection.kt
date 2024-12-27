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
import androidx.compose.foundation.lazy.grid.GridItemSpan
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.anime.live_wallpapershd.common.Constants
import com.anime.live_wallpapershd.model.Popular
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.ads.AdaptiveBannerAd
import com.anime.live_wallpapershd.presentation.ads.InterstitialAd
import com.anime.live_wallpapershd.presentation.loader.CircleLoading
import com.anime.live_wallpapershd.presentation.status.VideoStatus
import com.anime.live_wallpapershd.presentation.wallpapers.PopularViewModel


@Composable
fun PopularSection(
    viewModel: PopularViewModel,
    navController: NavController
)
{
    val popularList = viewModel.popularPager.collectAsLazyPagingItems()
    val context = LocalContext.current
    val interstitialAd: InterstitialAd = viewModel()
    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        contentPadding = PaddingValues(vertical = 5.dp, horizontal = 5.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .scale(1.01f)
            .padding(horizontal = 16.dp)
            .padding(bottom = 65.dp)
    ) {
        val totalItems = popularList.itemCount

        items(totalItems + totalItems / 4, span = { index ->
            // Iklan memenuhi 2 kolom, wallpaper hanya 1 kolom
            if ((index + 1) % 5 == 0) GridItemSpan(2) else GridItemSpan(1)
        }) { index ->
            val isAdPosition = (index + 1) % 5 == 0

            if (isAdPosition) {
                AdaptiveBannerAd(modifier = Modifier)
            } else {
                val adjustedIndex = index - (index / 5) // Hitung indeks yang disesuaikan untuk item wallpaper
                val item = popularList[adjustedIndex]
                item?.let {
                    PopularItem(popular = it) {
                        interstitialAd.onClickShowAd(context)
                        navController.navigate(Screen.WallpaperScreen.route + "/${item.id}")
                    }
                }
            }
        }
        when(popularList.loadState.append)
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

        when(popularList.loadState.refresh)
        {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item {
//                    LoadRefreshItem()
                }
            }

            is LoadState.Error ->
                item {
                    // TODO
                }
        }
    }
    if (popularList.loadState.refresh is LoadState.Loading){
        CircleLoading()
    }

}



@Composable
fun PopularItem(
    popular: Popular,
    onItemClick: (Popular)-> Unit
)
{

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
                onItemClick(popular)
            },
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            val context = LocalContext.current
            val imageUrl = popular.thumbnail
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

                    VideoStatus(videoUrl = popular.type)

                }
            }
        }
    }


}