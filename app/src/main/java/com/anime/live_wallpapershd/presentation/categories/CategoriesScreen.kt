package com.anime.live_wallpapershd.presentation.categories

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
import com.anime.live_wallpapershd.presentation.categories.components.CategoriesList
import com.anime.live_wallpapershd.ui.fonts.Fonts


@Composable
fun CategoriesScreen(
    viewmodel: CategoriesVieModel = hiltViewModel(),
    navController: NavController

    ) {
Column (
    modifier = Modifier
        .fillMaxSize()
) {
    Spacer(modifier = Modifier.height(35.dp))
    CategoriesTopBar(
        name = "Categories",
        navController = navController
    )
    Spacer(modifier = Modifier.height(15.dp))
    CategoriesList(viewmodel = viewmodel,navController = navController)

}

}


@Composable
fun  CategoriesTopBar(
    name: String,
    modifier: Modifier = Modifier,
    navController: NavController
)
{
    Row (
        verticalAlignment = Alignment.CenterVertically,

        modifier = modifier
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
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontFamily = Fonts.fontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 30.dp)
            )

    }

}