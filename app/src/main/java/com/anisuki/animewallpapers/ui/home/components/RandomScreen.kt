package com.anisuki.animewallpapers.ui.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anisuki.animewallpapers.ui.navgraph.Screen
import com.anisuki.animewallpapers.ui.home.RandomViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RandomScreen(
    navController: NavController,
    randomViewModel : RandomViewModel = hiltViewModel())

{
    val state by randomViewModel.state.collectAsState()

    LazyColumn {

        item {

                val wallpapers = state
                val pagerState = rememberPagerState(initialPage = 0) {
                    wallpapers.size
                }
                HorizontalPager(
                    
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)

                ) { index ->
                    val wallpaper = wallpapers[index]
                    Card(
                        shape = RoundedCornerShape(15.dp),
                        modifier =
                        Modifier
                            .padding(horizontal = 8.dp)
                    ) {

                        RandomItem(random = wallpaper){
                            navController.navigate(Screen.WallpaperScreen.route + "/${wallpaper.id}")
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(top = 12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .navigationBarsPadding(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    PageIndicator(
                        modifier = Modifier.width(65.dp),
                        pageSize = wallpapers.size,
                        selectedPage = pagerState.currentPage
                    )
                }
            }
        }
    }





