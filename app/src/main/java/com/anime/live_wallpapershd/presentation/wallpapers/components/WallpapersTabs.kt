package com.anime.live_wallpapershd.presentation.wallpapers.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.anime.live_wallpapershd.R
import com.anime.live_wallpapershd.model.Tabs
import com.anime.live_wallpapershd.ui.fonts.Fonts


@Composable
fun WallpapersTabs(
    tabsModel: List<Tabs>,
    onTabSelected : (selectedTabIndex: Int) -> Unit,
    navController: NavController

) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val inactiveColor = Color(0xFF777777)
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
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
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            contentColor = Color.Black,

            ) {
            tabsModel.forEachIndexed { index, item ->

                Tab(

                    selected = selectedTabIndex == index,
                    selectedContentColor = Color.Black,
                    unselectedContentColor = inactiveColor,
                    onClick = {
                        selectedTabIndex = index
                        onTabSelected(index)
                    }

                )


                {

                    Text(
                        text = item.title,
                        color = if (selectedTabIndex == index) Color.Black else inactiveColor,
                        fontFamily = Fonts.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(10.dp)

                    )
                }

            }


        }

    }
}



