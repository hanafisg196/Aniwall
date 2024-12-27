package com.anime.live_wallpapershd.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.ads.AdaptiveBannerAd
import com.anime.live_wallpapershd.presentation.ads.InterstitialAd
import com.anime.live_wallpapershd.presentation.loader.CircleLoading
import com.anime.live_wallpapershd.presentation.profile.component.ProfileSection
import com.anime.live_wallpapershd.presentation.wallpapers.components.LoadingItem
import com.anime.live_wallpapershd.presentation.wallpapers.components.WallpaperListItem

@Composable
fun WallpaperUserDetailScreen(
    navController: NavController,
    viewModelWallpapers : WallpapersUserDetailViewModel = hiltViewModel()
)
{
    val interstitialAd: InterstitialAd = viewModel()
    val wallpapersByUser = viewModelWallpapers.wallpaperPager.collectAsLazyPagingItems()
    val ownerState by viewModelWallpapers.ownerState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        interstitialAd.loadAd(context)
    }
    Column(modifier = Modifier.fillMaxWidth())
    {
        LaunchedEffect(Unit) {
            viewModelWallpapers.getWallpaperOwner()
        }
        Spacer(modifier = Modifier.height(35.dp))
        TopBar(navController)
        Spacer(modifier = Modifier.height(10.dp))
        ownerState?.let { owner ->
            ProfileSection(navController = navController,user = owner, total = wallpapersByUser.itemCount.toString())
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .scale(1.0f)
                .padding(horizontal = 16.dp)
        ) {
            val totalItems = wallpapersByUser.itemCount
            items(totalItems + totalItems / 4, span = { index ->
                if ((index + 1) % 5 == 0) GridItemSpan(2) else GridItemSpan(1)
            }) { index ->
                val isAdPosition = (index + 1) % 5 == 0

                if (isAdPosition){
                    AdaptiveBannerAd(modifier = Modifier)
                }else{
                    val adjustedIndex = index - (index / 5)
                    val item = wallpapersByUser[adjustedIndex]
                    item?.let {
                        WallpaperListItem(wallpapers = it) {
                            interstitialAd.onClickShowAd(context)
                            navController.navigate(Screen.WallpaperScreen.route + "/${item.id}")
                        }
                    }
                }
            }
            when(wallpapersByUser.loadState.append)
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

            when(wallpapersByUser.loadState.refresh)
            {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> {
                    item {
                        // TODO

                    }
                }

                is LoadState.Error ->
                    item {
                        // TODO
                    }
            }
        }
    }
    if (wallpapersByUser.loadState.refresh is LoadState.Loading){
        CircleLoading()
    }
}

@Composable
fun TopBar(
    navController: NavController,
){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)

    ){
        IconButton(
            onClick = {
              navController.navigateUp()
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.baseline_arrow_back_ios_24),
                contentDescription = "Back"
            )
        }

    }


}


