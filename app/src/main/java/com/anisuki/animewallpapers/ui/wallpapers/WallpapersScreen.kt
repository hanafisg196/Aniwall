package com.anisuki.animewallpapers.ui.wallpapers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anisuki.animewallpapers.R
import com.anisuki.animewallpapers.model.Tabs
import com.anisuki.animewallpapers.ui.wallpapers.components.LatestSection
import com.anisuki.animewallpapers.ui.wallpapers.components.WallpapersTabs

@Preview(showSystemUi = true)
@Composable
fun WallpapersScreen()
{
    var selectedTabIndex by remember{
        mutableIntStateOf(0)
    }

    Column {

        WallpapersTabs(
            tabsModel = listOf(
                Tabs(title = "Latest"),
                Tabs(title = "Popular"),
            ),
            onTabSelected = { selectedTabIndex = it },

            )
        Spacer(modifier = Modifier.height(10.dp))
        when (selectedTabIndex) {

            0 ->
                LatestSection(
                    wallpapers = listOf(
                        painterResource(id = R.drawable.satu),
                        painterResource(id = R.drawable.dua),
                        painterResource(id = R.drawable.tiga),
                        painterResource(id = R.drawable.empat),
                        painterResource(id = R.drawable.lima),
                        painterResource(id = R.drawable.enam),
                        painterResource(id = R.drawable.lima),
                        painterResource(id = R.drawable.enam),
                    )

                )
            1 -> LatestSection(
                wallpapers = listOf(
                    painterResource(id = R.drawable.empat),
                    painterResource(id = R.drawable.lima),
                    painterResource(id = R.drawable.satu),
                    painterResource(id = R.drawable.dua),
                    painterResource(id = R.drawable.tiga),
                    painterResource(id = R.drawable.enam),
                    painterResource(id = R.drawable.lima),
                    painterResource(id = R.drawable.enam),
                )

            )

        }

    }


}


