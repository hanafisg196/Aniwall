package com.anime.live_wallpapershd.presentation.profile.upload

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun PickVideo(
    videoUri: Uri?,
    onVideoSelected: (Uri?) -> Unit
){
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        onVideoSelected(it)
    }
        Button(onClick = {
            launcher.launch(
                PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.VideoOnly)
            )
        }) {
            Text(text = "Select Video")
        }

        videoUri?.let { video ->
            TextField(
                value = video.path.toString(),
                onValueChange = {video.path.toString()},
                textStyle = LocalTextStyle.current.copy(
                    textAlign = TextAlign.Left
                ),
                label = {
                    Text(text = "Video Path")
                },
                readOnly = true,
                modifier = Modifier.padding(top = 10.dp)

            )
        }
    }

