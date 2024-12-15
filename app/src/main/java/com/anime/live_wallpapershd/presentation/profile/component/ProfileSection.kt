package com.anime.live_wallpapershd.presentation.profile.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.anime.live_wallpapershd.model.User
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.home.RoundImage
import com.anime.live_wallpapershd.ui.fonts.Fonts

@Composable
fun ProfileSection(
    navController: NavController,
    modifier: Modifier = Modifier,
    user : User,
    total : String,
)
{
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ){
            RoundImage(
                image = rememberAsyncImagePainter(user.avatar),
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.size(30.dp))
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
            ){
                ProfileStat(name ="Posts", total = total)
            }
        }
        Spacer(modifier = Modifier.size(10.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = user.name,
                fontFamily = Fonts.fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Button(
                onClick = {  navController.navigate(Screen.ReportUserScreen.route + "/${user.id}") },

                modifier = Modifier
                    .height(35.dp)
            ) {
                Text(text = "Report")
            }
        }
            Log.d("ProfileSection", "ProfileSection: ${user.id}")
    }

}