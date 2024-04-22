package com.apptesting.test.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.apptesting.test.R
import com.apptesting.test.databinding.ActivitySplashBinding
import com.apptesting.test.utils.Global
import com.apptesting.test.utils.MyPlayStoreBilling
import com.apptesting.test.utils.RetrofitClient
import com.apptesting.test.utils.SessionManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding
    lateinit var disposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        disposable = CompositeDisposable()
        sessionManager = SessionManager(this)
        callApiForSettingsData()
        fetchSubscriptionDetails()

    }

    private fun fetchSubscriptionDetails() {
        var myPlayStoreBilling =
            MyPlayStoreBilling(this, object : MyPlayStoreBilling.OnPurchaseComplete {
                override fun onConnected(isConnect: Boolean) {

                }

                override fun onPurchaseResult(isPurchaseSuccess: Boolean) {

                }

                override fun onError(hasError: Boolean) {
                }
            })

        if (myPlayStoreBilling.isSubscriptionRunning) {
            sessionManager.setPremium(true)
        } else {
            sessionManager.setPremium(false)
        }
    }

    private fun callApiForSettingsData() {
        disposable.add(
            RetrofitClient.service.fetchAllData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .doOnTerminate {}
                .doOnError { throwable -> }
                .subscribe { getAllData, throwable ->
                    if (getAllData != null && getAllData.status!!) {
                        getAllData.admob?.let { it -> sessionManager.saveAdmob(it.first { it.type == 1 }) }
                        sessionManager.saveSubscriptions(getAllData.subscriptionPackages)
                        Global.allWallpapers = getAllData.wallpapers
                        Global.allCategories = getAllData.categories
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@SplashActivity,
                            getString(R.string.something_went_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        )
    }

}