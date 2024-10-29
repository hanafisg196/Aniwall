package com.anime.live_wallpapershd.presentation.profile.upload

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun PickVideo(
    videoUri: Uri?,
    onVideoSelected: (Uri?) -> Unit,
) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        onVideoSelected(it)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .width(300.dp)
            .padding(start = 30.dp, end = 30.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(16.dp)),

        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Max Image Size 20 Mb",
                color = Color.Black
            )
            Text(
                text = "Format Mp4 only",
                color = Color.Black
            )
            videoUri?.let { video ->
                Text(
                    text = "Video Path: ${video.path.toString()}",
                    color = Color.Black
                )
            }
            Button(onClick = {
                launcher.launch(
                    PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.VideoOnly)
                )
            }) {
                Text(text = "Select Video")
            }
            Spacer(modifier = Modifier.height(10.dp))

        }

    }
}

