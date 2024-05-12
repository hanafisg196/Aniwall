package com.anime.live_wallpapershd.ui

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@SuppressLint("InlinedApi")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(navController: NavHostController) {
    RequestPermissions(
        permissions = listOf(

            Manifest.permission.POST_NOTIFICATIONS

        ),
        deniedMessage = "Give this app permission to proceed. If it doesn't work, you'll have to do it manually from the settings.",
        rationaleMessage = "To use this app's functionalities, you need to give us the permission.",
        navController

    )
}
