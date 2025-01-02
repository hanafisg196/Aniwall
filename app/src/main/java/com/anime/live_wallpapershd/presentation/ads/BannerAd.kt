package com.anime.live_wallpapershd.presentation.ads

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.pixplicity.easyprefs.library.Prefs


@SuppressLint("SuspiciousIndentation")
@Composable

fun BannerAd(modifier: Modifier){
    val admobBannerId = Prefs.getString("admob_banner")
        AndroidView(
            modifier = modifier,
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.FULL_BANNER)
                    adUnitId = admobBannerId
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
}

@Composable
fun AdaptiveBannerAd(modifier: Modifier){
    val admobBannerId = Prefs.getString("admob_banner")
    AndroidView(
        modifier = modifier,
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(context.applicationContext, 320))
                adUnitId = admobBannerId
                adListener = object : AdListener() {
                    override fun onAdFailedToLoad(error: LoadAdError) {
                        Log.e("AdaptiveBannerAd", "Ad failed to load: ${error.message}")
                    }

                    override fun onAdLoaded() {
                        Log.d("AdaptiveBannerAd", "Ad loaded successfully.")
                    }
                }
                loadAd(AdRequest.Builder().build())

            }
        }
    )
}