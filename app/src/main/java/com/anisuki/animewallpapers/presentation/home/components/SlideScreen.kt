package com.anisuki.animewallpapers.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anisuki.animewallpapers.presentation.home.SlideViewModel


@Composable

fun SlideScreen(
    slideViewModel: SlideViewModel = hiltViewModel()
) {
    val state by slideViewModel.state.collectAsState()

    LazyRow {
        items(state.size) { index ->
            val slideItem = state[index]

            Box(
                modifier = Modifier
                    .padding(start = 15.dp, top = 2.dp, bottom = 15.dp)
                    .clickable {
//                      navController.navigate(route = Screen.TestScreen.route)
                    }
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .width(200.dp)
                    .height(100.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {

//                    var isLoading by remember {
//                        mutableStateOf(true)
//
//                    }
//                    LaunchedEffect(key1 = true) {
//                            delay(1000)
//                        isLoading = false
//                    }
//                    SlideShimmer(isLoading = isLoading) {
//
//                    }
                    SlideItem(slide = slideItem)
                }
            }
        }
    }
}





