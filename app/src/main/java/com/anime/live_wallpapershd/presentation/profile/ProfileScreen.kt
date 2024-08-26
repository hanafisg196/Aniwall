package com.anime.live_wallpapershd.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.model.User
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.home.RoundImage
import com.anime.live_wallpapershd.presentation.login.SignInViewModel
import com.anime.live_wallpapershd.ui.fonts.Fonts
import com.pixplicity.easyprefs.library.Prefs


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: UserProfileViewModel = hiltViewModel()
)
{

    val userState by viewModel.state.collectAsState()
    val token = Prefs.getString("token_auth")
    LaunchedEffect(token) {
        viewModel.getCurrentUser(token)

    }

    Column(modifier = Modifier.fillMaxWidth())
    {
        Spacer(modifier = Modifier.height(35.dp))
        TopBarProfile(navController)
        Spacer(modifier = Modifier.height(10.dp))
        userState?.let {user ->
            ProfileSection(user = user)
        }

    }
}



@Composable
fun TopBarProfile(
    navController: NavController,
    singInViewModel: SignInViewModel = hiltViewModel()
)
{
    val context = LocalContext.current
    Row (
        verticalAlignment = Alignment.CenterVertically,

        modifier = Modifier
            .fillMaxWidth()

    ) {
        IconButton(
            onClick = {
               // Todo:
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
            text = "Profile",
            overflow = TextOverflow.Ellipsis,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 30.dp)
        )
        IconButton(
            onClick = {
                // Todo:
            },
            modifier = Modifier.padding(start = 100.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.add),
                tint = Color.Black,
                contentDescription = "Back",
                modifier = Modifier.size(28.dp)

            )
        }
        IconButton(
            onClick = {
                singInViewModel.logout(onSuccess = {
                    navController.navigate(Screen.HomeScreen.route){
                        popUpTo(0)
                    }
                    Toast.makeText(context, "Logout Success", Toast.LENGTH_SHORT).show()
                }, onError = {
                    Toast.makeText(context, "Failed to Logout", Toast.LENGTH_SHORT).show()
                })
            },
            modifier = Modifier.padding(start = 35.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.exit),
                tint = Color.Black,
                contentDescription = "Back",
                modifier = Modifier.size(28.dp)

            )
        }

    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier,
    user : User
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
                ProfileStat(name ="Posts", number ="0")
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        ProfileDisplay(name = user.name)


    }

}



@Composable
fun ProfileStat(
    name: String,
    number: String
)
{
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Text(
            text = number,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
        )
        Text(
            text = name,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 18.sp,

        )

    }
}

@Composable
fun ProfileDisplay(
    name: String
)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        Text(
            text = name,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,

        )
    }

}


