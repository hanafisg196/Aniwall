package com.anisuki.animewallpapers.ui.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.anisuki.animewallpapers.ui.fonts.Fonts
import com.anisuki.animewallpapers.ui.home.pages


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WallpaperSlide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
        
    ) {
        val pagerState = rememberPagerState(initialPage = 0) {
            pages.size
        }
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .align(Alignment.CenterHorizontally)
        ) { index ->
            val wallpaper = pages[index]
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(horizontal = 8.dp)


            ) {
                Box(modifier = Modifier)
                {
                    val context = LocalContext.current
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(wallpaper.image)
                            .crossfade(true)
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .align(Alignment.BottomStart)


                    ) {
                        Text(
                            text = wallpaper.user,
                            color = Color.White,
                            fontFamily = Fonts.fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp,
                        )

                        Text(
                            text = wallpaper.description,
                            color = Color.White,
                            fontFamily = Fonts.fontFamily,
                            fontWeight = FontWeight.Light,
                            fontSize = 20.sp
                        )

                    }


                }

            }


        }
        Spacer(modifier = Modifier.padding(top = 12.dp))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.Center,

        ) {
            PageIndicator(
                modifier = Modifier
                    .width(65.dp),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )
        }
    }
}
