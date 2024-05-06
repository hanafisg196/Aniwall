package com.anisuki.animewallpapers.presentation.wallpapers.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
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
import com.anisuki.animewallpapers.model.Popular
import com.anisuki.animewallpapers.presentation.navgraph.Screen
import com.anisuki.animewallpapers.presentation.wallpapers.PopularViewModel


@Composable
fun PopularSection(
    viewModel: PopularViewModel,
    navController: NavController
)
{
    val popularList = viewModel.popularPager.collectAsLazyPagingItems()


    LazyVerticalGrid(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .scale(1.01f)
            .padding(horizontal = 16.dp)
    ) {
        items(popularList.itemCount) { index ->
            val item = popularList[index]
            item?.let {
                PopularItem(popular = it){
                    navController.navigate(Screen.WallpaperScreen.route + "/${item.id}")
                }
            }
        }
        when (popularList.loadState.append) {
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.Error -> {
                // Handle error state
                // TODO: Implement error handling
            }
            else -> Unit
        }



        // Handle empty list state
        if (popularList.itemCount == 0 && popularList.loadState.refresh !is LoadState.Loading) {
            item {
                // TODO: Show empty state UI
            }
        }
    }

}



@Composable
fun PopularItem(
    popular: Popular,
    onItemClick: (Popular)-> Unit
)
{

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .width(150.dp)
            .height(300.dp)
            .padding(vertical = 8.dp)
            .clickable {
                onItemClick(popular)
            }
    ) {
        val context = LocalContext.current
        val dataUrl = popular.thumbnail
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(Constants.ITEM_URL + dataUrl)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,

            )
    }


}