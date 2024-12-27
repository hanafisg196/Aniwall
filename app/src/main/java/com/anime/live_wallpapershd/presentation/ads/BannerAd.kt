package com.anime.live_wallpapershd.presentation.ads

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.pixplicity.easyprefs.library.Prefs


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
                setAdSize(AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(context.applicationContext, 400))
                adUnitId = admobBannerId
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}