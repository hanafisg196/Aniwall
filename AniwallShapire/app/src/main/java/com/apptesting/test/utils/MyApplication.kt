package com.apptesting.test.utils

import android.app.Activity
import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.apptesting.test.utils.ads.MyInterstitialAds
import com.apptesting.test.utils.ads.RewardAds

class MyApplication : Application() {
    var intAd: MyInterstitialAds? = null
    var rewardAd: RewardAds? = null
    override fun onCreate() {
        FirebaseApp.initializeApp(this)



        super.onCreate()
    }


    public fun initializeIntAd(context: Activity) {
        if (intAd == null) {
            intAd = MyInterstitialAds(context, false, object : MyInterstitialAds.OnShow {
                override fun onShow() {
                    intAd!!.initAds(false)
                    Log.i("TAG manali", "onShow: my app ")
                }

                override fun onFailed() {
                }

                override fun onClosed() {
                    Log.i("TAG manali", "onClosed: my app ")

                }
            })
        }
//        else {
//            if (intAd?.used == true) {
//
//
//            }
//        }
    }

    public fun initializeRewardAd(context: Activity) {
        if (rewardAd == null) {
            rewardAd = RewardAds(context)
        }

    }

}