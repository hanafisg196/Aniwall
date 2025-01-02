package com.anime.live_wallpapershd.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.presentation.home.components.WebViewScreen
import com.anime.live_wallpapershd.ui.fonts.Fonts


@Composable
fun PrivacyPoliceScreen(
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
            title = "Privacy Police"
        )
        Spacer(modifier = Modifier.height(12.dp))

            WebViewScreen(it.privacy_police)
        }

    }
}

@Composable
fun TopBar(
    navController: NavController,
    title:String
)
{
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()

    ) { IconButton(
        onClick = {
            navController.navigateUp()
        },
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "Back"
        )
    }
        Text(
            text = title,
            overflow = TextOverflow.Ellipsis,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 30.dp)
        )
    }

}