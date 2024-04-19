package com.anisuki.animewallpapers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.anisuki.animewallpapers.ui.home.HomeScreen
import com.anisuki.animewallpapers.ui.theme.AniwallTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AniwallTheme {
            HomeScreen()
            }
        }
    }
}



