package com.anime.live_wallpapershd.presentation.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.anime.live_wallpapershd.model.User
import com.anime.live_wallpapershd.presentation.home.RoundImage

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    user : User,
    total : String
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
        ProfileDisplay(name = user.name)


    }

}