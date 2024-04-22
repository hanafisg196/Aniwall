package com.apptesting.test.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.android.billingclient.api.BillingClient
import com.apptesting.test.R
import com.apptesting.test.databinding.ActivityPurchasePremiumBinding
import com.apptesting.test.model.AllData
import com.apptesting.test.utils.MyPlayStoreBilling
import io.reactivex.disposables.CompositeDisposable

class PurchasePremiumActivity : BaseActivity() {

    lateinit var binding: ActivityPurchasePremiumBinding
    lateinit var listOfSubs: List<AllData.SubscriptionPackagesItem>
    lateinit var myPlayStoreBilling: MyPlayStoreBilling


    var disposable = CompositeDisposable()
    lateinit var selectedPackage: AllData.SubscriptionPackagesItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_purchase_premium)


        initView()
        initListeners()


    }

    private fun initListeners() {

        binding.btnSubscribe.setOnClickListener {
            if (selectedPackage != null && selectedPackage.androidProductId!!.isNotEmpty()) {
                binding.frameLout.visibility = View.VISIBLE
                myPlayStoreBilling.startPurchase(
                    selectedPackage.androidProductId,
                    BillingClient.ProductType.SUBS,
                    false
                )

            } else {

                Toast.makeText(
                    this@PurchasePremiumActivity,
                    getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()


            }
        }

        binding.frameLout.setOnClickListener { v -> }


        binding.cardYearly.setOnClickListener { view ->

            binding.selectedMonthly = false
            selectedPackage = listOfSubs[1]

        }

        binding.cardMonthly.setOnClickListener { view ->

            binding.selectedMonthly = true

            selectedPackage = listOfSubs[0]

        }

        binding.imgBack.setOnClickListener { view -> onBackPressed() }

    }

    private fun initView() {


        listOfSubs = sessionManager.subscription
        selectedPackage = listOfSubs[1]


        binding.tvMonthPrice.text = listOfSubs[1].price + listOfSubs[1].currency

        binding.tvYearPrice.text = listOfSubs[0].price + listOfSubs[0].currency




        myPlayStoreBilling =
            MyPlayStoreBilling(this, object : MyPlayStoreBilling.OnPurchaseComplete {
                override fun onConnected(isConnect: Boolean) {

                }

                override fun onPurchaseResult(isPurchaseSuccess: Boolean) {

                    binding.frameLout.visibility = View.GONE

                    if (isPurchaseSuccess) {

                        sessionManager.setPremium(true)
                        startActivity(
                            Intent(
                                this@PurchasePremiumActivity,
                                SplashActivity::class.java
                            )
                        )
                        finishAffinity()

                    } else {

                        runOnUiThread {
                            binding.frameLout.visibility = View.GONE
                            Toast.makeText(
                                this@PurchasePremiumActivity,
                                getString(R.string.something_went_wrong),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                }

                override fun onError(hasError: Boolean) {
                    if (hasError) {
                        runOnUiThread {
                            binding.frameLout.visibility = View.GONE
                            Toast.makeText(
                                this@PurchasePremiumActivity,
                                getString(R.string.something_went_wrong),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }


                }
            })


    }
}