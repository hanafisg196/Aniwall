package com.anisuki.animewallpapers.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anisuki.animewallpapers.presentation.categories.CategoriesScreen
import com.anisuki.animewallpapers.presentation.categories.WallpapersByCatScreen
import com.anisuki.animewallpapers.presentation.detail.WallpaperScreen
import com.anisuki.animewallpapers.presentation.home.HomeScreen
import com.anisuki.animewallpapers.presentation.home.components.RandomScreen
import com.anisuki.animewallpapers.presentation.wallpapers.WallpapersScreen
import com.anisuki.animewallpapers.ui.PermissionScreen


@Composable
fun SetNav(navController: NavHostController)
{
    NavHost(navController = navController,
        startDestination = Screen.PermissionScreen.route
    ) {
        composable(
            route = Screen.PermissionScreen.route
        ){
            PermissionScreen(navController)
        }
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
            WallpaperScreen(wallpaperId = wallpaperId, navController = navController)
        }

        composable(
            route = Screen.WallpapersScreen.route
        ){
           WallpapersScreen(navController = navController)
        }

        composable(
            route = Screen.CategoriesScreen.route
        ){
            CategoriesScreen(navController = navController)
        }

        composable(
            route = Screen.WallpapersByCatScreen.route + "/{id}"
        ) {
                navBackStackEntry ->
            val categoryId = navBackStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            WallpapersByCatScreen( navController = navController)
        }

    }
}