package com.anime.live_wallpapershd.navgraph

sealed class Screen (val route: String)  {
      object PermissionScreen :  Screen(route = "permission")
      object HomeScreen :  Screen(route = "home")
      object RandomScreen :  Screen(route = "random")
      object WallpaperScreen : Screen(route = "wallpaper/{id}")
      object WallpapersScreen :  Screen(route = "wallpapers")
      object CategoriesScreen :  Screen(route = "categories")
      object WallpapersByCatScreen :  Screen(route = "categories/{id}")
      object ProfileScreen :  Screen(route = "profile")
      object LoginScreen :  Screen(route = "login")
      object FavoriteScreen :  Screen(route = "favorite")
      object UploadVideoScreen :  Screen(route = "upload_video")
      object UploadImageScreen :  Screen(route = "upload_image")
      object WallpaperUserDetail :  Screen(route = "wallpaper_user_detail/{id}")
      object ReportWallpaperScreen :  Screen(route = "report_wallpaper/{id}")
      object ReportUserScreen :  Screen(route = "report_user/{id}")
      object SlideWallpapersScreen :  Screen(route = "slide_wallpapers/{id}")
      object SearchScreen :  Screen(route = "search_wallpapers")
      object SettingScreen :  Screen(route = "setting")
      object PrivacyPoliceScreen :  Screen(route = "privacy_police")
      object TermServiceScreen :  Screen(route = "term_service")
}