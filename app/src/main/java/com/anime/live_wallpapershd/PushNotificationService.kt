package com.anime.live_wallpapershd

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class PushNotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d(TAG, "From: ${message.from}")

        val title = message.data["title"] ?: "Notification"
        val body = message.data["body"] ?: "You have a new notification."
        val deeplink = message.data["link"] ?: return

        Log.d(TAG, "Wallpaper Intent: $deeplink")
        Log.d(TAG, "Wallpaper Click ACTION: $deeplink")

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = System.currentTimeMillis().toInt()
        val channelId = "Firebase Messaging ID"
        val channelName = "Firebase Messaging"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            )
        }

        val deepLinkIntent = Intent(Intent.ACTION_VIEW, deeplink.toUri())
        val deepLinkPendingIntent: PendingIntent? = TaskStackBuilder.create(applicationContext).run {
            addNextIntentWithParentStack(deepLinkIntent)
            getPendingIntent(1234, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.google)
            .setAutoCancel(true)
            .setContentIntent(deepLinkPendingIntent)
            .build()

        notificationManager.notify(notificationId, notification)
    }

//    override fun onNewToken(token: String) {
//        super.onNewToken(token)
//        // Handle token update (if necessary)
//    }

    companion object {
        private const val TAG = "PushNotificationService"
    }
}
