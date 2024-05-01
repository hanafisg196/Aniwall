package com.anisuki.animewallpapers.ui.home.components



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.anisuki.animewallpapers.R
import com.anisuki.animewallpapers.common.Constants.ITEM_URL
import com.anisuki.animewallpapers.model.Random
import com.anisuki.animewallpapers.ui.fonts.Fonts


@Composable
fun RandomItem(
    random: Random,
    onItemClick: (Random) -> Unit
)
{
    Box(
        modifier =
        Modifier
            .clickable {
                 onItemClick(random)
            }
    ) {
        val context = LocalContext.current
        val imageUrl = random.thumbnail
//        Log.d("wallpaper", "Trying to load thumbnail from URL: $imageUrl")
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(ITEM_URL + imageUrl)
                .crossfade(true)
                .scale(Scale.FILL)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds,

        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopStart)
        ) {

            VideoTypeText(videoUrl = random.type)

        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = random.title,
                color = Color.White,
                fontFamily = Fonts.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,

            )
        }
    }

}

@Composable
fun VideoTypeText(videoUrl: String) {
    val text = if (videoUrl.contains(".mp4")) {
        "Video"
    } else {
        null 
    }

    if (text != null) {
        Icon(
            painter = painterResource(id = R.drawable.play),
            contentDescription = text,
            modifier =
            Modifier
                .size(50.dp)
            ,
            tint = colorResource(id = R.color.pinkCustom )
        )
    }
}