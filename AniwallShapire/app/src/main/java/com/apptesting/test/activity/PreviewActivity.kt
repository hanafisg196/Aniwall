package com.apptesting.test.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.apptesting.test.R
import com.apptesting.test.databinding.ActivityPreviewBinding
import com.apptesting.test.databinding.ItemPremiumPopupBinding
import com.apptesting.test.model.AllData
import com.apptesting.test.utils.Const
import com.apptesting.test.utils.MyApplication
import com.apptesting.test.utils.ads.MyInterstitialAds
import com.apptesting.test.utils.ads.RewardAds


class PreviewActivity : BaseActivity() {

    lateinit var binding: ActivityPreviewBinding
    lateinit var selectedWall: AllData.WallpapersItem
    var player: ExoPlayer? = null
    var rewardEarned = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_preview)



        initView()
        initListeners()


    }

    private fun initListeners() {
        binding.loutLoader.setOnClickListener {

        }

        binding.loutLoaderDownload.setOnClickListener {

        }

        binding.btnDownload.setOnClickListener {


            if (!sessionManager.getPremium()) {

                if (selectedWall.accessType == 2) {


                    val myApplication: MyApplication = getApplication() as MyApplication

                    myApplication.intAd?.showAds(object : MyInterstitialAds.OnShow {
                        override fun onShow() {
                            Log.i("TAG manali", "onShow: preview ")
                            myApplication.intAd?.initAds(false)

                        }

                        override fun onFailed() {
                            startDownload()
                        }

                        override fun onClosed() {
                            Log.i("TAG manali", "onClosed: preview ")

                            startDownload()
                        }
                    })
                } else if (selectedWall.accessType == 1) {

                    loadRewardAd()


                } else {
                    showPremiumPopup()
                }


            } else {
                startDownload()
            }

        }

        binding.cardClose.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
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

    private fun loadRewardAd() {
        val myApplication: MyApplication = getApplication() as MyApplication

        myApplication.rewardAd?.showAd()
        myApplication.rewardAd?.rewardAdListnear = object : RewardAds.RewardAdListnear {
            override fun onAdClosed() {
                if (rewardEarned) {


                    startDownload()

                }
                myApplication.rewardAd?.initGoogle()


            }

            override fun onEarned() {
                rewardEarned = true
            }
        }
    }

    private fun startDownload() {
        binding.loutLoaderDownload.visibility = View.VISIBLE
        downloadWall(selectedWall, object : OnDownload {
            override fun onComplete() {
                binding.loutLoaderDownload.visibility = View.GONE

            }

            override fun onError() {
                binding.loutLoaderDownload.visibility = View.GONE
            }
        })
    }


    private fun initView() {


        if (!sessionManager.getPremium()) {
            binding.loutLoader.visibility = View.VISIBLE
            val myApplication: MyApplication = getApplication() as MyApplication


            myApplication.intAd?.showAds(object : MyInterstitialAds.OnShow {
                override fun onShow() {
                    Log.i("TAG manali", "onShow: preview ")
                    myApplication.intAd?.initAds(false)

                }

                override fun onFailed() {
                    binding.loutLoader.visibility = View.GONE

                }

                override fun onClosed() {
                    Log.i("TAG manali", "onClosed: preview ")

                    binding.loutLoader.visibility = View.GONE

                }
            })
        }


        var s = intent.getStringExtra(Const.Key.wallpaper)
        if (s != null) {
            selectedWall = Gson().fromJson(s, AllData.WallpapersItem::class.java)


            if (selectedWall.wallpaperType == 0) {

                binding.exoPlayerView.visibility = View.GONE
                binding.img.visibility = View.VISIBLE

                setBlur(binding.blurView1, binding.rootLout)
                setBlur(binding.blurView2, binding.rootLout)
                Glide.with(this@PreviewActivity).load(
                    Const.ITEM_URL + selectedWall.content!!
                ).apply(
                    RequestOptions().error(
                        R.color.transparent
                    ).priority(Priority.HIGH)
                ).into(binding.img)
            } else {
                binding.btnDownload.setCardBackgroundColor(this.getColorStateList(R.color.color_theme_blue_50))
                binding.cardClose.setCardBackgroundColor(this.getColorStateList(R.color.color_theme_blue_50))

                initPlayer(Const.ITEM_URL + selectedWall.content!!)
            }

        }

    }

    private fun initPlayer(s: String) {

        binding.exoPlayerView.visibility = View.VISIBLE
        binding.img.visibility = View.GONE

        binding.exoPlayerView.player?.release()
        player = ExoPlayer.Builder(this)
            .build()

        binding.exoPlayerView.player = player

        val mediaItem = MediaItem.fromUri(Uri.parse(s))

        player?.playWhenReady = true
        player?.repeatMode = Player.REPEAT_MODE_ALL
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.play()
        player?.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {

                Log.i("TAG", "onPlayerError: " + error)
                super.onPlayerError(error)
            }
        })
    }

    override fun onDestroy() {

        if (player != null) {
            player?.release()

        }

        super.onDestroy()
    }
}