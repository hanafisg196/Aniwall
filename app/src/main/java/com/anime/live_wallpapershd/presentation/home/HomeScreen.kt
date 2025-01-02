package com.anime.live_wallpapershd.presentation.home


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.model.BottomMenus
import com.anime.live_wallpapershd.navgraph.Screen
import com.anime.live_wallpapershd.presentation.dialogs.DialogLogin
import com.anime.live_wallpapershd.presentation.dialogs.DialogMenu
import com.anime.live_wallpapershd.presentation.home.components.ButtonMenu
import com.anime.live_wallpapershd.presentation.home.components.RandomScreen
import com.anime.live_wallpapershd.presentation.slide.SlideScreen
import com.anime.live_wallpapershd.ui.fonts.Fonts
import com.anime.live_wallpapershd.ui.theme.MyBlue
import com.pixplicity.easyprefs.library.Prefs


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController:NavHostController
) {
    val token = Prefs.getString("token_auth")
    var showDialog by remember { mutableStateOf(false)}

    if (showDialog) {
        DialogLogin(
            onClick = {
                navController.navigate(Screen.LoginScreen.route)
            },
            onDismiss = { showDialog = false }
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()

    )
    {

        Spacer(modifier = Modifier.height(35.dp))
        TopBar(navController,name = "Aniwall", token)
        Spacer(modifier = Modifier.height(6.dp))
        SlideScreen(navController = navController)
        RandomScreen(navController = navController)
        val items = listOf(
            BottomMenus(R.drawable.category),
            BottomMenus(R.drawable.compass),
            BottomMenus(R.drawable.heart),
            BottomMenus(R.drawable.gear),

        )
        ButtonMenu(items = items) { clickedItem ->

                when(clickedItem.iconId){
                    R.drawable.category -> {
                        navController.navigate(Screen.CategoriesScreen.route)
                    }
                    R.drawable.compass -> {
                        navController.navigate(Screen.WallpapersScreen.route)
                    }
                    R.drawable.heart -> {

                        if (token.isNullOrEmpty())
                        {
                            showDialog = true
                        } else {
                            navController.navigate(Screen.FavoriteScreen.route)
                        }

                    }
                    R.drawable.gear -> {
                        navController.navigate(Screen.SettingScreen.route)
                    }
                }
           }
        }
    }

@Composable
fun TopBar(
    navController:NavHostController,
    name: String,
    token: String,
    modifier: Modifier = Modifier

)
{
    val profilePicture = Prefs.getString("profile_image")
    var showDialogMenu by remember { mutableStateOf(false)}
    var showDialog by remember {mutableStateOf(false)}
    if (showDialog){
        DialogLogin(
            onClick = {
                navController.navigate(Screen.LoginScreen.route)
            },
            onDismiss = {showDialog = false}
        )
    }
    if (showDialogMenu){
        DialogMenu(
            onDismiss = { showDialogMenu = false }
        )
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    )
    {
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,

        )

        Spacer(modifier = Modifier
            .weight(1f))

        RoundImage(
            image = if (token.isNotEmpty()){
                rememberAsyncImagePainter(profilePicture)
            }else{
                painterResource(id = R.drawable.profile)
            },
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    if (token.isNotEmpty()) {
                        navController.navigate(Screen.ProfileScreen.route)
                    } else {
                        showDialog = true
                    }
                }
        )

        Spacer(modifier = Modifier
            .width(15.dp))

        Icon(
            painter = painterResource(id = R.drawable.find),
            contentDescription = "Search",
            tint = MyBlue,
            modifier = Modifier.size(31.dp)
                .clickable {
                    navController.navigate(Screen.SearchScreen.route)
                }
        )
    }
}

@Composable
fun RoundImage(
    image : Painter,
    modifier: Modifier = Modifier
)
{
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}



