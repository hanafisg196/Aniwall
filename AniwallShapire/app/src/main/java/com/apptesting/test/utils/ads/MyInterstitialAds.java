package com.apptesting.test.utils.ads;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.apptesting.test.utils.SessionManager;

public class MyInterstitialAds {

    private static final String TAG = "IntAdSphere";
    public InterstitialAd mInterstitialAd;
    public OnShow onShow;
    public boolean used = false;
    SessionManager sessionManager;
    private Context context;


    public MyInterstitialAds(Context context, Boolean showWhenLoaded, OnShow onShow) {
        this.context = context;
        this.onShow = onShow;
        sessionManager = new SessionManager(context);
        if (!sessionManager.getPremium()) {
            initAds(showWhenLoaded);
        }
    }

    public void initAds(Boolean showWhenLoaded) {
        Log.i("TAG manali", "add initialized again ");

        sessionManager = new SessionManager(context);
        if (sessionManager.getAdmob() == null || sessionManager.getAdmob().getIntersialId() == null) {
            return;
        }
        InterstitialAd.load(context, sessionManager.getAdmob().getIntersialId(), new AdRequest.Builder().build(), new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                if (onShow != null) {
                    onShow.onFailed();
                }
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                mInterstitialAd = interstitialAd;

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {

                        if (onShow != null) {
                            onShow.onClosed();
                        }
                        super.onAdDismissedFullScreenContent();
                    }
                });
                Log.i(TAG, "initAds: add initialized");
                used = false;
                if (showWhenLoaded) {
                    showAds(new OnShow() {
                        @Override
                        public void onShow() {

                        }

                        @Override
                        public void onFailed() {

                        }

                        @Override
                        public void onClosed() {

                        }
                    });
                }
            }


        });
    }

    public void showAds(OnShow onShow) {
        this.onShow = onShow;
        if (mInterstitialAd != null) {

            mInterstitialAd.show((Activity) context);
            used = true;
            if (this.onShow != null) {
                this.onShow.onShow();

            }
        }
    }


    public interface OnShow {
        void onShow();

        void onFailed();

        void onClosed();
    }
}
