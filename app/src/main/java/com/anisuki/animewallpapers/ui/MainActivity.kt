package com.anisuki.animewallpapers.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.anisuki.animewallpapers.ui.detail.WallpaperScreen
import com.anisuki.animewallpapers.ui.theme.AniwallTheme
import com.anisuki.animewallpapers.ui.wallpapers.WallpapersScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
 private lateinit var navController : NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AniwallTheme {
//               navController = rememberNavController()
//                SetNav(navController)
               WallpapersScreen()

            }
        }
    }
}



