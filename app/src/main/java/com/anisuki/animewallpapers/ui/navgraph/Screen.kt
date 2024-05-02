package com.anisuki.animewallpapers.ui.navgraph

import com.anisuki.animewallpapers.common.Constants.BASE_URL

sealed class Screen (val route: String)  {
     object HomeScreen :  Screen(route = "home")
     object RandomScreen :  Screen(route = "random")
     object WallpaperScreen : Screen(route = "wallpaper/{id}")
     object WallpapersScreen :  Screen(route = "wallpapers")


}