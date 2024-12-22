package com.anime.live_wallpapershd.navgraph

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.anime.live_wallpapershd.presentation.categories.CategoriesScreen
import com.anime.live_wallpapershd.presentation.categories.WallpapersByCatScreen
import com.anime.live_wallpapershd.presentation.categories.WallpapersByCatViewModel
import com.anime.live_wallpapershd.presentation.detail.WallpaperScreen
import com.anime.live_wallpapershd.presentation.detail.WallpaperUserDetailScreen
import com.anime.live_wallpapershd.presentation.detail.WallpapersUserDetailViewModel
import com.anime.live_wallpapershd.presentation.home.HomeScreen
import com.anime.live_wallpapershd.presentation.home.components.RandomScreen
import com.anime.live_wallpapershd.presentation.login.LoginScreen
import com.anime.live_wallpapershd.presentation.profile.ProfileScreen
import com.anime.live_wallpapershd.presentation.profile.UploadImageScreen
import com.anime.live_wallpapershd.presentation.profile.UploadVideoScreen
import com.anime.live_wallpapershd.presentation.report.ReportUserScreen
import com.anime.live_wallpapershd.presentation.report.ReportWallpaperScreen
import com.anime.live_wallpapershd.presentation.slide.SlideWallpapersScreen
import com.anime.live_wallpapershd.presentation.slide.SlideWallpapersViewModel
import com.anime.live_wallpapershd.presentation.wallpapers.FavoriteScreen
import com.anime.live_wallpapershd.presentation.wallpapers.WallpapersScreen
import com.anime.live_wallpapershd.ui.PermissionScreen


@Composable
fun SetNav(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.PermissionScreen.route
    ) {
        composable(
            route = Screen.PermissionScreen.route
        ) {
            PermissionScreen(navController)
        }
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Screen.RandomScreen.route
        ) {
            RandomScreen(navController)
        }
        composable(
            route = Screen.WallpaperScreen.route + "/{id}",
            deepLinks = listOf(navDeepLink {
                uriPattern = "kyoani-publisher.xyz/wallpaper/{id}"
                action = Intent.ACTION_VIEW
            })
        ) { navBackStackEntry ->
            val wallpaperId = navBackStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            WallpaperScreen(wallpaperId = wallpaperId, navController = navController)
        }

        composable(
            route = Screen.WallpapersScreen.route
        ) {
            WallpapersScreen(navController = navController)
        }

        composable(
            route = Screen.CategoriesScreen.route
        ) {
            CategoriesScreen(navController = navController)
        }

        composable(
            route = Screen.WallpapersByCatScreen.route + "/{id}"
        ) { navBackStackEntry ->
            val categoryId = navBackStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            val viewModel: WallpapersByCatViewModel = hiltViewModel()
            viewModel.catId = categoryId
            WallpapersByCatScreen(navController = navController)
        }

        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen(navController = navController)
        }

        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController = navController)
        }

        composable(
            route = Screen.FavoriteScreen.route
        ) {
            FavoriteScreen(navController = navController)
        }
        composable(
            route = Screen.UploadImageScreen.route
        ) {
            UploadImageScreen(navController = navController)
        }

        composable(
            route = Screen.UploadVideoScreen.route
        ) {
            UploadVideoScreen(navController = navController)
        }
        composable(
            route = Screen.WallpaperUserDetail.route + "/{id}"
        ) { navBackStackEntry ->
            val ownerId = navBackStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            val viewModel: WallpapersUserDetailViewModel = hiltViewModel()
            viewModel.ownerId = ownerId
            WallpaperUserDetailScreen(navController = navController)
        }

        composable(
            route = Screen.ReportWallpaperScreen.route + "/{id}"
        ) { navBackStackEntry ->
            val wallpaperId = navBackStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            ReportWallpaperScreen(wallpaperId, navController = navController)
        }

        composable(
            route = Screen.ReportUserScreen.route + "/{id}"
        ) { navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            val viewModel: WallpapersUserDetailViewModel = hiltViewModel()
            viewModel.ownerId = userId
            ReportUserScreen( navController = navController)

        }
        composable(
            route = Screen.SlideWallpapersScreen.route + "/{id}"
        ) { navBackStackEntry ->
            val slideId = navBackStackEntry.arguments?.getString("id")?.toIntOrNull() ?: -1
            val viewModel : SlideWallpapersViewModel = hiltViewModel()
            viewModel.slideId = slideId
            SlideWallpapersScreen(navController = navController)
        }
    }
}