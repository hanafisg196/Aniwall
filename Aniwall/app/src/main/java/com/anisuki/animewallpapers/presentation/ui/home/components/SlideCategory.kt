package com.anisuki.animewallpapers.presentation.ui.home.components


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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.anisuki.animewallpapers.presentation.ui.fonts.Fonts
import com.anisuki.animewallpapers.presentation.ui.home.catItems


@Composable
fun SlideCategory() {
    var selectedCategory by remember { mutableIntStateOf(0) }

    LazyRow {
        items(catItems.size) { index ->
            Box(
                modifier = Modifier
                    .padding(start = 15.dp, top = 2.dp, bottom = 15.dp)
                    .clickable {
                        selectedCategory = index
                    }
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(15.dp))
                    .width(200.dp)
                    .height(90.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(modifier = Modifier)
                    {
                        val context = LocalContext.current

                        AsyncImage(
                            model = ImageRequest.Builder(context)
                                .data(catItems[index].image)
                                .crossfade(true)
                                .scale(Scale.FILL)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds
                        )
                        Column (
                            modifier = Modifier
                                .padding(8.dp)
                                .align(Alignment.BottomStart)

                        )
                        {
                            Text(
                                text = catItems[index].title,
                                color = Color.White,
                                fontFamily = Fonts.fontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp
                            )
                        }
                    }




                }
            }
        }
    }
}




