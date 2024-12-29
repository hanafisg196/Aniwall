package com.anime.live_wallpapershd.presentation.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anime.live_wallpapershd.ui.fonts.Fonts

@Composable
fun DialogMenu(
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { /*TODO*/ },
        modifier = Modifier
            .height(400.dp),
        title = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Aniwall",
                    color = Color.Black,
                    fontFamily = Fonts.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
                ) {

                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Privacy Policy Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Privacy Policy",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Privacy Policy Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Privacy Policy",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Privacy Policy Icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Privacy Policy",
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }


            }
        }
    )
}
