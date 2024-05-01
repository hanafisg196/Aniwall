package com.anisuki.animewallpapers.ui

import com.anisuki.animewallpapers.common.Constants.BASE_URL

sealed class Screen (val route: String)  {
     object HomeScreen :  Screen(route = "home")
     object RandomScreen :  Screen(route = "random")
     object WallpaperScreen : Screen(route = "wallpaper/{id}")
     object SlideScreen :  Screen(route = "slide")
     object TestScreen :  Screen(route = "test")
     object TestDetail :  Screen(route = "testDetail")

}