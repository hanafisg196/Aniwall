package com.anime.live_wallpapershd.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.anime.live_wallpapershd.presentation.navgraph.SetNav
import com.anime.live_wallpapershd.ui.theme.AniwallTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
 private lateinit var navController : NavHostController

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )

        )
        super.onCreate(savedInstanceState)


        setContent {

            AniwallTheme {

               navController = rememberNavController()
                SetNav(navController)


            }
        }
    }
}



