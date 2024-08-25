package com.anime.live_wallpapershd.presentation.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anime.live_wallpapershd.ui.fonts.Fonts

@Composable
fun DialogLogin(
    onClick: () -> Unit,
    onDismiss: () -> Unit,

) {

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { /*TODO*/ },
        modifier = Modifier
            .height(300.dp),
        title = {
            Column {
                Text(
                    text = "Welcome to Aniwall",
                    color = Color.Black,
                    fontFamily = Fonts.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Justify

                )
                Text(
                    text = "Hello, you don't sign in. Sign in to access profile, favorites and upload features.",
                    color = Color.Black,
                    fontFamily = Fonts.fontFamily,
                    fontWeight = FontWeight.Thin,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify,
                )


            }
        },
        text = {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                       onClick()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Yes",
                        color = Color.White,
                        fontFamily = Fonts.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,

                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    onClick = {

                        onDismiss()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "No",
                        color = Color.White,
                        fontFamily = Fonts.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        
                    )
                }
            }

        }
    )
}
