package com.apptesting.test.utils

import androidx.annotation.Keep
import com.apptesting.test.BuildConfig

@Keep
object Const {
    const val BASE = "http://192.168.18.42:8000/"
//    const val BASE = "https://aniwall.kyoani-publisher.xyz/"
    const val APIKEY = "123"
    const val BASE_URL = BASE + "api/"
    const val ITEM_URL = BASE + "public/storage/"
    const val TERMS_URL = BASE + "termsOfUse"
    const val PRIVACY_URL = BASE + "privacyPolicy"
    const val NOTIFICATION_TOPIC = "sphere"

    object ApiParams {
        const val fetchAllData = "fetchAllData"
        const val apikey = "apikey"

    }

    object Key {
        const val settings = "settings"
        const val is_old = "is_old"
        const val data = "data"
        const val subscriptions = "subscriptions"
        const val admob = "admob"
        const val is_notification = "is_notification"
        const val is_premium = "is_premium"
        const val favourites = "favourites"
        const val dataList = "dataList"
        const val wallpaper = "wallpaper"
        const val position = "position"
        const val PREF_NAME = BuildConfig.APPLICATION_ID
    }
}