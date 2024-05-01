package com.anisuki.animewallpapers.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import com.anisuki.animewallpapers.R
import com.anisuki.animewallpapers.model.ButtonMenuContent
import com.anisuki.animewallpapers.ui.fonts.Fonts
import com.anisuki.animewallpapers.ui.home.components.ButtonMenu
import com.anisuki.animewallpapers.ui.home.components.RandomScreen
import com.anisuki.animewallpapers.ui.home.components.SlideScreen


@Composable
fun HomeScreen(navController:NavHostController)
{
    Column (
        modifier = Modifier
            .fillMaxSize()

    )
    {
             Spacer(modifier = Modifier.height(4.dp))
             TopBar(name = "Aniwall")
             Spacer(modifier = Modifier.height(6.dp))
             SlideScreen(navController)
             RandomScreen(navController)
             Spacer(modifier = Modifier.height(13.dp))
            ButtonMenu(items =  listOf(
            ButtonMenuContent(R.drawable.category),
            ButtonMenuContent(R.drawable.compass),
            ButtonMenuContent(R.drawable.search),
            ButtonMenuContent(R.drawable.heart),

        )
            )
    }
}

@Composable
fun TopBar(
    name: String,
    modifier: Modifier = Modifier
)
{
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
            image = painterResource(id = R.drawable.profile),
            modifier = Modifier
                .size(40.dp)
        )

        Spacer(modifier = Modifier
            .width(15.dp))

        Icon(
            painter = painterResource(id = R.drawable.menu_bar),
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier.size(22.dp)
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
