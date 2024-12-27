package com.anime.live_wallpapershd.presentation.slide.component



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.anime.live_wallpapershd.common.Constants.ITEM_URL
import com.anime.live_wallpapershd.model.Slide
import com.anime.live_wallpapershd.ui.fonts.Fonts

@Composable
    fun  SlideItem(slide: Slide ) {
    Box(modifier = Modifier)
    {
        val context = LocalContext.current
        val imageUrl = slide.image

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(ITEM_URL+imageUrl)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column (
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomCenter)

        )
        {
            Text(
                text = slide.name,
                color = Color.White,
                fontFamily = Fonts.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
    }






