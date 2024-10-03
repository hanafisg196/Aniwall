package com.anime.live_wallpapershd.presentation.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anime.live_wallpapershd.navgraph.Screen


@Composable
fun DialogUpload (
    navController: NavController,
    onDismiss: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { /*TODO*/ },
        modifier = Modifier.height(350.dp),
        title = {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription =  "Upload Wallpaper"
                )
            }
        },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),

                ) {
                Button(
                    onClick = { navController.navigate(Screen.UploadImageScreen.route) },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(10.dp)
                ) {
                    Text(text = "Upload Image")

                }

                Button(
                    onClick = { navController.navigate(Screen.UploadVideoScreen.route) },
                    modifier = Modifier
                        .width(180.dp)
                        .padding(10.dp)
                ) {
                    Text(text = "Upload Video")

                }
            }
        }

    )

}