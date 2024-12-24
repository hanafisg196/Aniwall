package com.anime.live_wallpapershd


import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.anime.live_wallpapershd.navgraph.SetNav
import com.anime.live_wallpapershd.presentation.home.NotificationViewModel
import com.anime.live_wallpapershd.ui.theme.AniwallTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.messaging
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController : NavHostController
    private val notificationViewModel: NotificationViewModel by viewModels()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        intent?.data?.let { uri ->
            Log.d(TAG, "Received deep link in onCreate: $uri")

//            val screen = uri.getQueryParameter("screen")
//            val id = uri.getQueryParameter("id")
//
//            if (screen == "details" && id != null) {
//                navController.navigate("details/$id")
//            }
        }
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
        OnCompleteListener{ task ->
           if (!task.isSuccessful){
               notificationViewModel.sendNotificationToken(task.result)
               Log.w("FCM", "Fetching FCM registration token failed", task.exception)
               return@OnCompleteListener
           }
            task.result?.let { token ->
                notificationViewModel.sendNotificationToken(token)
                Log.d(TAG, "FCM Token: $token")
            }
        })
        Firebase.messaging.subscribeToTopic("global")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
//                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
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