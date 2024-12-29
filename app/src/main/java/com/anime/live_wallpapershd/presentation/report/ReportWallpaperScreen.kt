package com.anime.live_wallpapershd.presentation.report

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.presentation.detail.WallpaperViewModel
import com.anime.live_wallpapershd.presentation.report.component.DescriptionField
import com.anime.live_wallpapershd.presentation.report.component.EmailField
import com.anime.live_wallpapershd.ui.fonts.Fonts


@Composable
fun ReportWallpaperScreen(
    wallpaperId: Int,
    wallpaperViewModel: WallpaperViewModel = hiltViewModel(),
    sendReportViewModel: ReportViewModel = hiltViewModel(),
    navController: NavController
){
    val isSendRequest by sendReportViewModel.isSendRequest.collectAsState()
    val errorEmail by sendReportViewModel.errorEmail.collectAsState()
    val errorDescription by sendReportViewModel.errorDescription.collectAsState()
    val wallpaperState by wallpaperViewModel.state.collectAsState()
    var email by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        wallpaperViewModel.getWallpaper(wallpaperId)
    }

    Column(modifier = Modifier.fillMaxWidth())
    {
        Spacer(modifier = Modifier.height(35.dp))
        TopBar(navController = navController, title = "Report Wallpaper")
        Spacer(modifier = Modifier.height(35.dp))
        wallpaperState?.let { wallpaper ->
            Text(text = "Title : ${wallpaper.title}",
                modifier = Modifier.padding(start = 30.dp))
            Text(text = "From : ${wallpaper.users.name}",
                modifier = Modifier.padding(start = 30.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))
        EmailField(email = email) {
            email = it
        }
        errorEmail?.let {
            Text(
                text = it,
                modifier = Modifier.padding(start = 30.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        DescriptionField(
            description = description)
        {
            description = it
        }
        errorDescription?.let {
            Text(
                text = it,
                modifier = Modifier.padding(start = 30.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (isSendRequest){
            CircularProgressIndicator(
                modifier = Modifier.
                align(Alignment.CenterHorizontally)
                    .padding(bottom = 10.dp)
            )
        } else {
            Button(
                onClick = {
                    sendReportViewModel.sendWallpaperReport(
                        wallpaperId,
                        email,
                        description
                    ){
                        navController.popBackStack()
                        Toast.makeText(context, "Report Wallpaper Success", Toast.LENGTH_SHORT).show()
                    }

                },
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Send Report")
            }
        }

    }

}

@Composable
fun TopBar(
    navController: NavController,
    title: String
){
    Row (
        verticalAlignment = Alignment.CenterVertically,

        modifier = Modifier
            .fillMaxWidth()

    ){
        IconButton(
            onClick = {
                navController.navigateUp()
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = R.drawable.baseline_arrow_back_ios_24),
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