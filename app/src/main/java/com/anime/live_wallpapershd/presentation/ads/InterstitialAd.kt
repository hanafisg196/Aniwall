package com.anime.live_wallpapershd.presentation.ads

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.pixplicity.easyprefs.library.Prefs

class InterstitialAd: ViewModel() {
    private var mInterstitialAd: InterstitialAd? = null
    private var clickCount = mutableIntStateOf(0)
    private var interstitialId = Prefs.getString("admob_interstitial")
    private var interstitialClick = Prefs.getInt("interstitial_click")
    fun loadAd(context: Context) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(context, interstitialId, adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("Ad", "Ad failed to load: ${adError.message}")
               mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                Log.d("Ad", "Ad successfully loaded.")
              mInterstitialAd = interstitialAd
            }
        })
    }
    fun showAd(context: Context) {
        val activityContext = context as? Activity
        activityContext?.let { activity ->
            mInterstitialAd?.let {
                it.show(activity)
                it.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdClicked() {
                        Log.d(TAG, "Ad was clicked.")
                        loadAd(context)
                    }
                }
            }
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(context)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
        }

    }
    fun onClickShowAd(context: Context) {
        clickCount.intValue++
        if (clickCount.intValue >= interstitialClick) {
            mInterstitialAd?.let {
                (context as? Activity)?.let { activity ->
                    it.show(activity)
                    clickCount.intValue = 0
                }
            }
        }
        Log.d("TAG", "TotalClick : ${clickCount.intValue}")
    }
}