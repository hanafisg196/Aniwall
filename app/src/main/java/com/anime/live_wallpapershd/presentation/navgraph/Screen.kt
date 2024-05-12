package com.anime.live_wallpapershd.presentation.navgraph

sealed class Screen (val route: String)  {
     object PermissionScreen :  Screen(route = "permission")
     object HomeScreen :  Screen(route = "home")
     object RandomScreen :  Screen(route = "random")
     object WallpaperScreen : Screen(route = "wallpaper/{id}")
     object WallpapersScreen :  Screen(route = "wallpapers")
     object CategoriesScreen :  Screen(route = "categories")
     object WallpapersByCatScreen :  Screen(route = "categories/{id}")


}