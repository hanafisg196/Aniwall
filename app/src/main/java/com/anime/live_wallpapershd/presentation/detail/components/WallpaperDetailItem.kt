package com.anime.live_wallpapershd.presentation.detail.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.common.Constants.ITEM_URL
import com.anime.live_wallpapershd.model.Wallpaper
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.detail.FavoriteViewModel
import com.anime.live_wallpapershd.presentation.dialogs.DialogLogin
import com.pixplicity.easyprefs.library.Prefs

@Composable
fun WallpaperDetailItem(
    wallpaper: Wallpaper,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),

)
{
    val token = Prefs.getString("token_auth")
    val isFavorite by favoriteViewModel.checkFavorite.collectAsState()
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!token.isNullOrEmpty()) {
            favoriteViewModel.checkFavorite(token, wallpaper.id)
        }
    }
    val color = if (isFavorite) {
        Color.Red
    } else {
        Color.Gray
    }
    if (showDialog){
        DialogLogin(
            onClick = {
                navController.navigate(Screen.LoginScreen.route)
            },
            onDismiss = {showDialog = false}
        )
    }
    Box(
        modifier = Modifier
    ) {
        val dataUrl = ITEM_URL+ wallpaper.type
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(dataUrl)
                .crossfade(true)
                .size(Size.ORIGINAL)
                .scale(Scale.FILL)
                .build(),

            )

        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.Center),
                strokeWidth = 5.dp
            )
        }


        Image(
            painter = painter,
            contentDescription = "wallpaper",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, start = 25.dp, end = 25.dp)
                .align(Alignment.TopStart)
        ) {
            Box(modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .clickable {
                    navController.navigateUp()
                }
            ){
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = null,
                    modifier =
                    Modifier
                        .size(15.dp)
                        .align(Alignment.Center),
                    Color.Black,

                    )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .clickable {
                    if (token.isNullOrEmpty()) {
                        showDialog = true
                    } else {
                        if (isFavorite) {
                            favoriteViewModel.removeFavorite(token, wallpaper.id)
                            Toast.makeText(context, "Wallpaper removed from favorites", Toast.LENGTH_SHORT).show()
                        } else {
                            favoriteViewModel.addFavorite(token, wallpaper.id)
                            Toast.makeText(context, "Wallpaper add to favorites", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            ){
                Icon(
                    painter = painterResource(R.drawable.heart),
                    contentDescription = null,
                    modifier =
                    Modifier
                        .size(20.dp)
                        .align(Alignment.Center),
                    tint = color
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Box(modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .clickable {
                    navController.navigate(Screen.ReportWallpaperScreen.route)
                }

            ) {
                Icon(
                    painter = painterResource(R.drawable.flag),
                    contentDescription = null,
                    modifier =
                    Modifier
                        .size(20.dp)
                        .align(Alignment.Center),
                    Color.Gray
                )
            }

        }

        BottomSheet(wallpaper,token, wallpaper.user_id, navController)
    }


}