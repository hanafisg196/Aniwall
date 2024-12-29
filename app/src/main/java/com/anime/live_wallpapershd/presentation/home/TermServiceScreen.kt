package com.anime.live_wallpapershd.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.anime.live_wallpapershd.presentation.home.components.WebViewScreen


@Composable
fun TermServiceScreen(
    navController: NavController
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        TopBar(
            navController = navController,
            title = "Privacy Police"
        )
        Spacer(modifier = Modifier.height(12.dp))
        WebViewScreen("https://www.freepik.com/free-photos-vectors/notification")

    }
}

