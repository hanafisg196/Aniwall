package com.anime.live_wallpapershd

import android.app.Application
import android.content.ContentValues.TAG
import android.content.ContextWrapper
import android.util.Log
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.pixplicity.easyprefs.library.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AniwallApplication : Application()
{
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        PRDownloader.initialize(applicationContext);
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            subscribeToTopic("global")
        })

        val config = PRDownloaderConfig.newBuilder()
            .setDatabaseEnabled(true)
            .build()
        PRDownloader.initialize(applicationContext, config)
        val configWithTimeout = PRDownloaderConfig.newBuilder()
            .setReadTimeout(30_000)
            .setConnectTimeout(30_000)
            .build()

        PRDownloader.initialize(applicationContext, configWithTimeout)
         Prefs.Builder()
        .setContext(this)
        .setMode(ContextWrapper.MODE_PRIVATE)
        .setPrefsName(packageName)
        .setUseDefaultSharedPreference(true)
        .build()


}

}

private fun subscribeToTopic(topic: String) {
    FirebaseMessaging.getInstance().subscribeToTopic(topic)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("FCM", "Subscribed to topic: $topic")
            } else {
                Log.e("FCM", "Failed to subscribe to topic: $topic", task.exception)
            }
        }
}
