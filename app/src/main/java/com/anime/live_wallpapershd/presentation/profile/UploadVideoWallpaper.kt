package com.anime.live_wallpapershd.presentation.profile

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.profile.upload.PickVideo
import com.anime.live_wallpapershd.presentation.profile.upload.TitleWallpaper
import com.anime.live_wallpapershd.services.createMultipartBodyImage
import com.anime.live_wallpapershd.services.createMultipartBodyVideo
import com.anime.live_wallpapershd.ui.fonts.Fonts
import com.pixplicity.easyprefs.library.Prefs


@Composable
fun UploadVideoScreen(
    viewModel: UploadWallpaperViewModel = hiltViewModel(),
    navController: NavController
){
    val context = LocalContext.current
    val isUploading by viewModel.isUploading.collectAsState()
    val token = Prefs.getString("token_auth")
    var videoUri by remember { mutableStateOf<Uri?>(null) }
    var title by remember { mutableStateOf("") }
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        UploadVideoTopBar(name = "Upload Video" )
        Spacer(modifier = Modifier.height(35.dp))
        TitleWallpaper(
            titleText = title,
            onTitleChange = { title = it }
        )
        Spacer(modifier = Modifier.height(35.dp))
        PickVideo(
            videoUri = videoUri ,
            onVideoSelected = {videoUri = it}
        )
        Spacer(modifier = Modifier.height(35.dp))
        if (isUploading){
            CircularProgressIndicator(
                modifier = Modifier.
                align(Alignment.CenterHorizontally)
            )
        }else{
            Button(onClick = {

                videoUri?.let { uri ->
                    val videoPart = createMultipartBodyVideo(context, uri, "type")
                    if (videoPart != null) {
                        viewModel.uploadWallpaper(
                            token ?: "",
                            title,
                            1,
                            videoPart
                        ){
                            navController.navigate(Screen.ProfileScreen.route)
                            Toast.makeText(context, "Upload Success", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Log.e("Upload", "Upload Error")
                    }

                }
            }) {
                Text(text = "Upload")
            }
        }
    }

}


@Composable
fun  UploadVideoTopBar(
    name: String,
    modifier: Modifier = Modifier,
)
{
    Row (
        verticalAlignment = Alignment.CenterVertically,

        modifier = modifier
            .fillMaxWidth()

    ) { IconButton(
        onClick = {
            //Todo
        },
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "Back"
        )
    }

        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 30.dp)
        )

    }

}