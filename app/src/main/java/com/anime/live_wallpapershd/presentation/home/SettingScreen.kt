package com.anime.live_wallpapershd.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.ui.fonts.Fonts


@Composable
fun SettingScreen(
    navController: NavController,
    settingViewModel: SettingViewModel = hiltViewModel()
){
    val settingState by settingViewModel.settingState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        SettingTopBar()
        Spacer(modifier = Modifier.height(30.dp))
        settingState?.let { item ->
//            Row (
//                modifier = Modifier
//                    .padding(start = 20.dp, end = 20.dp)
//                    .fillMaxWidth()
//                    .clickable {
//                        //Todo
//                    },
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically,
//            ){
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Notifications,
//                        contentDescription = "Icon",
//                        modifier = Modifier.size(24.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Notification" ,
//                        color = Color.Black,
//                        fontFamily = Fonts.fontFamily,
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.SemiBold,
//                    )
//                }
//
//            }
//            Spacer(modifier = Modifier.height(30.dp))
//            Row (
//                modifier = Modifier
//                    .padding(start = 20.dp, end = 20.dp)
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ){
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//
//                    ) {
//                    Icon(
//                        painter = painterResource(R.drawable.trash),
//                        contentDescription = "Icon",
//                        modifier = Modifier.size(24.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Clear Cache" ,
//                        color = Color.Black,
//                        fontFamily = Fonts.fontFamily,
//                        fontSize = 16.sp,
//                        fontWeight = FontWeight.SemiBold,
//                    )
//                }
//                Text(text = "20 MB")
//            }
//            Spacer(modifier = Modifier.height(30.dp))
            Row (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screen.PrivacyPoliceScreen.route)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,

            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Privacy Police" ,
                        color = Color.Black,
                        fontFamily = Fonts.fontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Text(
                    text = item.privacy_police ,
                    color = Color.Gray,
                    fontFamily = Fonts.fontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Thin,
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(Screen.TermServiceScreen.route)
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Term & Service" ,
                        color = Color.Black,
                        fontFamily = Fonts.fontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Text(
                    text = item.term_service ,
                    color = Color.Gray,
                    fontFamily = Fonts.fontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Thin,
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Icon(
                        painter = painterResource(R.drawable.developer),
                        contentDescription = "Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Developer" ,
                        color = Color.Black,
                        fontFamily = Fonts.fontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Text(
                    text = item.developer ,
                    color = Color.Gray,
                    fontFamily = Fonts.fontFamily,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Thin,
                )
            }

            Spacer(modifier = Modifier.height(30.dp))
            Row (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Icon(
                        painter = painterResource(R.drawable.email),
                        contentDescription = "Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Email" ,
                        color = Color.Black,
                        fontFamily = Fonts.fontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Text(
                    text = item.email ,
                    color = Color.Gray,
                    fontFamily = Fonts.fontFamily,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Thin,
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Icon(
                        painter = painterResource(R.drawable.web),
                        contentDescription = "Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Website" ,
                        color = Color.Black,
                        fontFamily = Fonts.fontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Text(
                    text = item.website ,
                    color = Color.Gray,
                    fontFamily = Fonts.fontFamily,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Thin,
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row (
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Icon(
                        painter = painterResource(R.drawable.app),
                        contentDescription = "Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "App Version" ,
                        color = Color.Black,
                        fontFamily = Fonts.fontFamily,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Text(
                    text = item.app_version ,
                    color = Color.Gray,
                    fontFamily = Fonts.fontFamily,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Thin,
                )
            }

        }

    }

}


@Composable
fun SettingTopBar(
)
{
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()

    ) { IconButton(
        onClick = {
           //Todo
        },
        modifier = Modifier.padding(start = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
            contentDescription = "Back"
        )
    }
        Text(
            text = "Setting",
            overflow = TextOverflow.Ellipsis,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 30.dp)
        )
    }

}