package com.anisuki.animewallpapers.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anisuki.animewallpapers.ui.detail.WallpaperScreen
import com.anisuki.animewallpapers.ui.home.HomeScreen
import com.anisuki.animewallpapers.ui.home.components.RandomScreen


@Composable
fun SetNav(navController: NavHostController)
{
    NavHost(navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {

        composable(
            route = Screen.HomeScreen.route
        ){
            HomeScreen(navController)
        }

        composable(
            route = Screen.RandomScreen.route
        ){
            RandomScreen(navController)
        }
        composable(
            route = Screen.WallpaperScreen.route + "/{id}"
        ) {
            navBackStackEntry ->
            val wallpaperId = navBackStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            WallpaperScreen(wallpaperId = wallpaperId)
        }



    }
}