package com.anisuki.animewallpapers.presentation.wallpapers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.anisuki.animewallpapers.model.Tabs
import com.anisuki.animewallpapers.presentation.wallpapers.components.LatestSection
import com.anisuki.animewallpapers.presentation.wallpapers.components.PopularSection
import com.anisuki.animewallpapers.presentation.wallpapers.components.WallpapersTabs


@Composable
fun WallpapersScreen(
    viewModelLatest: WallpapersViewModel = hiltViewModel(),
    viewModelPopular: PopularViewModel = hiltViewModel(),
    navController: NavController
)
{

    var selectedTabIndex by remember{
        mutableIntStateOf(0)
    }

    Column {
        Spacer(modifier = Modifier.height(35.dp))
        WallpapersTabs(
            tabsModel = listOf(
                Tabs(title = "Latest"),
                Tabs(title = "Popular"),
            ),
            onTabSelected = { selectedTabIndex = it },
            navController = navController
            )
        Spacer(modifier = Modifier.height(10.dp))
        when (selectedTabIndex) {

            0 -> LatestSection(viewModel = viewModelLatest,navController)
            1 -> PopularSection(viewModel = viewModelPopular,navController)

        }

    }


}


