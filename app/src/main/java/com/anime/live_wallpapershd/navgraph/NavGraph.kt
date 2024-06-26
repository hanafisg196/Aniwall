package com.anime.live_wallpapershd.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anime.live_wallpapershd.presentation.categories.CategoriesScreen
import com.anime.live_wallpapershd.presentation.categories.WallpapersByCatScreen
import com.anime.live_wallpapershd.presentation.categories.WallpapersByCatViewModel
import com.anime.live_wallpapershd.presentation.detail.WallpaperScreen
import com.anime.live_wallpapershd.presentation.home.HomeScreen
import com.anime.live_wallpapershd.presentation.home.components.RandomScreen
import com.anime.live_wallpapershd.presentation.login.LoginScreen
import com.anime.live_wallpapershd.presentation.profile.ProfileScreen
import com.anime.live_wallpapershd.presentation.wallpapers.WallpapersScreen
import com.anime.live_wallpapershd.ui.PermissionScreen


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
            val viewModel: WallpapersByCatViewModel = hiltViewModel()
            viewModel.catId = categoryId
            WallpapersByCatScreen( navController = navController)
        }

        composable(
            route = Screen.ProfileScreen.route
        ){
            ProfileScreen(navController)
        }

        composable(
            route = Screen.LoginScreen.route
        ){
            LoginScreen(navController)
        }

    }
}