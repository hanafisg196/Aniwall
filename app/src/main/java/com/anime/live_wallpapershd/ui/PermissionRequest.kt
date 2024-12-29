package com.anime.live_wallpapershd.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.accompanist.permissions.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.anime.live_wallpapershd.navgraph.Screen


@ExperimentalPermissionsApi
@Composable
fun RequestPermissions(
    permissions: List<String>,
    deniedMessage: String = "Give this app a permission to proceed. If it doesn't work, then you'll have to do it manually from the settings.",
    rationaleMessage: String = "To use this app's functionalities, you need to give us the permission.",
    navController: NavHostController,
    modifier: Modifier

) {
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)

    HandleRequests(
        multiplePermissionsState = multiplePermissionsState,
        deniedContent = { shouldShowRationale ->
            PermissionDeniedContent(
                deniedMessage = deniedMessage,
                rationaleMessage = rationaleMessage,
                shouldShowRationale = shouldShowRationale,
                onRequestPermission = { multiplePermissionsState.launchMultiplePermissionRequest() }
            )
        },
        navController = navController,


    )
}


@ExperimentalPermissionsApi
@Composable
private fun HandleRequests(
    multiplePermissionsState: MultiplePermissionsState,
    deniedContent: @Composable (Boolean) -> Unit,
    navController: NavHostController
) {
    var shouldShowRationale by remember { mutableStateOf(false) }
    val permissionGranted = multiplePermissionsState.permissions.all {
        shouldShowRationale = it.status.shouldShowRationale
        it.status == PermissionStatus.Granted
    }
    LaunchedEffect(permissionGranted) {
        if (permissionGranted) {
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo(Screen.PermissionScreen.route) { inclusive = true }
            }
        }
    }
    if (!permissionGranted) {
        deniedContent(shouldShowRationale)
    }
}