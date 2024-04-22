package com.apptesting.test.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.github.islamkhsh.CardSliderViewPager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.apptesting.test.R
import com.apptesting.test.databinding.ActivityViewWallpapersBinding
import com.apptesting.test.databinding.ItemPremiumPopupBinding
import com.apptesting.test.model.AllData
import com.apptesting.test.utils.Const
import com.apptesting.test.utils.MyApplication
import com.apptesting.test.utils.ads.MyInterstitialAds
import com.apptesting.test.utils.ads.RewardAds
import com.apptesting.test.viewmodel.ViewWallpapersModel

class ViewWallpapersActivity : BaseActivity() {

    lateinit var binding: ActivityViewWallpapersBinding
    lateinit var model: ViewWallpapersModel
    var dataList: List<AllData.WallpapersItem> = arrayListOf()
    var pos = 0
    var rewardEarned = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_wallpapers)
        model = ViewModelProvider(this)[ViewWallpapersModel::class.java]

        initView()
        initListeners()

        binding.viewPager.setCurrentItem(pos, false)

        binding.model = model
    }


    private fun startDownload() {
        binding.loutLoaderDownload.visibility = View.VISIBLE
        downloadWall(dataList[binding.viewPager.currentItem], object : OnDownload {
            override fun onComplete() {
                binding.loutLoaderDownload.visibility = View.GONE

            }

            override fun onError() {
                binding.loutLoaderDownload.visibility = View.GONE
            }
        })
    }

    private fun loadRewardAd() {

        val myApplication: MyApplication = getApplication() as MyApplication

        myApplication.rewardAd?.showAd()
        myApplication.rewardAd?.rewardAdListnear = object : RewardAds.RewardAdListnear {
            override fun onAdClosed() {
                Log.i("TAG", "onAdClosed: $rewardEarned")

                if (rewardEarned) {


                    startDownload()
                }
                myApplication.rewardAd?.initGoogle()
                rewardEarned = false

            }

            override fun onEarned() {
                Log.i("TAG", "onEarned: ")
                rewardEarned = true
            }
        }
    }

    private fun showPremiumPopup() {
        val dialog = Dialog(this)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = LayoutInflater.from(this).inflate(R.layout.item_premium_popup, null, false)
        val binding: ItemPremiumPopupBinding = DataBindingUtil.bind(view)!!
        if (binding != null) {
            binding.btnSubscribe.setOnClickListener { view2 ->
                startActivity(Intent(this, PurchasePremiumActivity::class.java))
                dialog.dismiss()
            }
            binding.btnCancel.setOnClickListener { view3 -> dialog.dismiss() }
            dialog.setContentView(view)
            dialog.setCancelable(false)
            dialog.show()
        }
    }

    private fun initListeners() {

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.loutLoaderDownload.setOnClickListener {

        }

        binding.btnDownload.setOnClickListener {

            if (!sessionManager.getPremium()) {

                if (dataList[binding.viewPager.currentItem].accessType == 2) {
                    val myApplication: MyApplication = application as MyApplication

                    myApplication.intAd?.showAds(object : MyInterstitialAds.OnShow {
                        override fun onShow() {
                            Log.i("TAG manali", "onShow: view wall ")
                            myApplication.intAd?.initAds(false)

                        }

                        override fun onFailed() {
                            startDownload()
                        }

                        override fun onClosed() {
                            Log.i("TAG manali", "onClosed: view wall ")

                            startDownload()
                        }
                    })
                } else if (dataList[binding.viewPager.currentItem].accessType == 1) {
                    loadRewardAd()
                } else {
                    showPremiumPopup()

                }
            } else {
                startDownload()
            }

        }

        binding.btnPreview.setOnClickListener {

            var intent = Intent(this, PreviewActivity::class.java)

            intent.putExtra(
                Const.Key.wallpaper,
                Gson().toJson(dataList[binding.viewPager.currentItem])
            )

            startActivity(intent)
        }

        binding.viewPager.registerOnPageChangeCallback(object :
            com.github.islamkhsh.viewpager2.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {


                var img_url: String = ""
                img_url = if (dataList[position].wallpaperType == 0) {
                    dataList[position].content!!
                } else {
                    dataList[position].thumbnail!!

                }

                Glide.with(this@ViewWallpapersActivity).load(Const.ITEM_URL + img_url).apply(
                    RequestOptions().error(
                        R.color.transparent
                    ).priority(Priority.HIGH)
                ).into(binding.imgBg)

                super.onPageSelected(position)

            }
        })

    }

    private fun initView() {

        binding.viewPager.adapter = model.viewWallpapersAdapter
        setBlur(binding.blurView, binding.rootLout)

        pos = intent.getIntExtra(Const.Key.position, 0)
        var s = intent.getStringExtra(Const.Key.dataList)
        if (s != null) {
            dataList = Gson().fromJson(
                s, object : TypeToken<List<AllData.WallpapersItem>?>() {}.type
            )

            model.viewWallpapersAdapter.updateData(dataList)
            binding.viewPager.autoSlideTime = CardSliderViewPager.STOP_AUTO_SLIDING
        }

    }
}