package com.anime.live_wallpapershd.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anime.live_wallpapershd.presentation.home.components.WebViewScreen


@Composable
fun TermServiceScreen(
    navController: NavController,
    settingViewModel: SettingViewModel = hiltViewModel()
){
    val settingState by settingViewModel.settingState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        settingState?.let {
        TopBar(
            navController = navController,
            title = "Term and Service"
        )
        Spacer(modifier = Modifier.height(12.dp))

            WebViewScreen(it.term_service)
        }


    }
}

