package com.anime.live_wallpapershd.presentation.wallpapers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.ads.AdaptiveBannerAd
import com.anime.live_wallpapershd.presentation.ads.BannerAd
import com.anime.live_wallpapershd.presentation.ads.InterstitialAd
import com.anime.live_wallpapershd.presentation.loader.CircleLoading
import com.anime.live_wallpapershd.presentation.wallpapers.components.LoadingItem
import com.anime.live_wallpapershd.presentation.wallpapers.components.SearchBar
import com.anime.live_wallpapershd.presentation.wallpapers.components.WallpaperListItem
import com.anime.live_wallpapershd.ui.fonts.Fonts


@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {
    val searchResult = viewModel.searchPage.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val interstitialAd: InterstitialAd = viewModel()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        interstitialAd.loadAd(context)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier
                .fillMaxSize())
        {
            Spacer(modifier = Modifier.height(35.dp))
            SearchTopBar(
                navController = navController,
                searchQuery = searchQuery,
                onQueryChange = { newQuery ->
                    viewModel.updateSearchQuery(newQuery)
                })
            Spacer(modifier = Modifier.height(10.dp))
            if (searchResult.itemCount == 0)
            {
                EmptyState()
            } else {
                LazyVerticalGrid(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .scale(1.01f)
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 70.dp)
                ) {
                    val totalItems = searchResult.itemCount
                    items(totalItems + totalItems / 4, span = { index ->
                        if ((index + 1) % 5 == 0) GridItemSpan(2) else GridItemSpan(1)
                    }) { index ->
                        val isAdPosition = (index + 1) % 5 == 0

                        if (isAdPosition) {
                            AdaptiveBannerAd(modifier = Modifier)
                        } else {
                            val adjustedIndex = index - (index / 5)
                            val item = searchResult[adjustedIndex]
                            item?.let {
                                WallpaperListItem(wallpapers = it) {
                                    interstitialAd.onClickShowAd(context)
                                    navController.navigate(Screen.WallpaperScreen.route + "/${item.id}")
                                }
                            }
                        }
                    }
                    when(searchResult.loadState.append)
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
                }
                if (searchResult.loadState.refresh is LoadState.Loading){
                    CircleLoading()
                }
            }
        }
        BannerAd(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 2.dp))
    }


}


@Composable
fun  SearchTopBar(
    navController: NavController,
    searchQuery: String,
    onQueryChange: (String) -> Unit
)
{
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()

    ) { IconButton(
        onClick = {
           navController.navigateUp()
        },
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "Back"
        )
    }
        SearchBar(searchQuery = searchQuery,
            onQueryChange = onQueryChange
        )

    }

}

@Composable
fun EmptyState() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No results found",
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.Thin,
            fontSize = 16.sp,
            color = Color.Black,
            )
    }
}