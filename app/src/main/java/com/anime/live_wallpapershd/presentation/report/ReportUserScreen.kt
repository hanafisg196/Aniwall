package com.anime.live_wallpapershd.presentation.report

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anime.live_wallpapershd.presentation.detail.WallpapersUserDetailViewModel
import com.anime.live_wallpapershd.presentation.report.component.DescriptionField
import com.anime.live_wallpapershd.presentation.report.component.EmailField


@Composable
fun ReportUserScreen(
    navController: NavController,
    viewModelWallpapers : WallpapersUserDetailViewModel = hiltViewModel(),
    sendReport: ReportViewModel = hiltViewModel(),

){
    val userState by viewModelWallpapers.ownerState.collectAsState()
    val isSendRequest by sendReport.isSendRequest.collectAsState()
    val errorEmail by sendReport.errorEmail.collectAsState()
    val errorDescription by sendReport.errorDescription.collectAsState()
    var email by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModelWallpapers.getWallpaperOwner()
        }

    Column(modifier = Modifier.fillMaxWidth())
    {
        Spacer(modifier = Modifier.height(35.dp))
        TopBar(title = "Report User")
        Spacer(modifier = Modifier.height(35.dp))
        userState?.let { user ->
            Text(text = "Name : ${user.name}",
                modifier = Modifier.padding(start = 30.dp))
            Text(text = "Email : ${user.email}",
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
                    sendReport.sendUserReport(
                        userState!!.id,
                        email,
                        description
                    ) {
                        navController.popBackStack()
                        Toast.makeText(context, "Report User Success", Toast.LENGTH_SHORT).show()
                    }

                },
                modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(text = "Send Report")
            }
        }

    }
}