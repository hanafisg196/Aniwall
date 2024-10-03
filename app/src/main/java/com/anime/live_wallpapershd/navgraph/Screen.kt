package com.anime.live_wallpapershd.navgraph

sealed class Screen (val route: String)  {
     data object PermissionScreen :  Screen(route = "permission")
     data object HomeScreen :  Screen(route = "home")
     data object RandomScreen :  Screen(route = "random")
     data object WallpaperScreen : Screen(route = "wallpaper/{id}")
     data object WallpapersScreen :  Screen(route = "wallpapers")
     data object CategoriesScreen :  Screen(route = "categories")
     data object WallpapersByCatScreen :  Screen(route = "categories/{id}")
     data object ProfileScreen :  Screen(route = "profile")
     data object LoginScreen :  Screen(route = "login")
     data object FavoriteScreen :  Screen(route = "favorite")
     data object UploadVideoScreen :  Screen(route = "upload_video")
     data object UploadImageScreen :  Screen(route = "upload_image")
}