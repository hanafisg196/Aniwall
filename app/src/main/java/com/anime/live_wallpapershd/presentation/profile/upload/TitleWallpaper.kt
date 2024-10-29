package com.anime.live_wallpapershd.presentation.profile.upload

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TitleWallpaper(
    titleText: String,
    onTitleChange: (String) -> Unit,
) {
    TextField(
        value = titleText,
        onValueChange = onTitleChange,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Left
        ),
        label = {
            Text(text = "Title")
        },
        placeholder = {
            Text(text = "Wallpaper Title")
        },
        maxLines = 1,
        modifier = Modifier.fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
    )
}