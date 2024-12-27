package com.anime.live_wallpapershd.model

import com.google.gson.annotations.SerializedName

data class Ads(
    @SerializedName("admob_app_id")
    val admobId : String,
    @SerializedName("admob_banner")
    val admobBanner : String,
    @SerializedName("admob_native")
    val admobNative : String,
    @SerializedName("admob_interstitial")
    val admobInterstitial : String,
    @SerializedName("admob_open")
    val admobOpen : String,
    @SerializedName("admob_reward")
    val admobReward : String,
    @SerializedName("interstitial_click")
    val interstitialClick : Int,
    @SerializedName("native_item")
    val nativeItem : Int,
    @SerializedName("refresh_stat")
    val refresh : Boolean,

)
