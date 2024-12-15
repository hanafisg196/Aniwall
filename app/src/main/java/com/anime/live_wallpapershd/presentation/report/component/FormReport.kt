package com.anime.live_wallpapershd.presentation.report.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp




@Composable
fun EmailField(
    email: String,
    onEmailChange: (String) -> Unit
){
    TextField(
        value = email,
        onValueChange = onEmailChange,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Left
        ),
        label = {
            Text(text = "Email")
        },
        placeholder = {
            Text(text = "Your Email")
        },
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
    )
}

@Composable
fun DescriptionField(
    description: String,
    onDescriptionChange: (String) -> Unit
){
    TextField(
        value = description,
        onValueChange = onDescriptionChange,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Left
        ),
        label = {
            Text(text = "Description")
        },
        placeholder = {
            Text(text = "Describe you reason ..")
        },
        maxLines = 5,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 30.dp, end = 30.dp)
    )
}