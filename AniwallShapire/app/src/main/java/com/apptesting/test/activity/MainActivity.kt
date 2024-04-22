package com.apptesting.test.activity

import android.Manifest
import android.animation.ValueAnimator
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.messaging.FirebaseMessaging
import com.apptesting.test.BuildConfig
import com.apptesting.test.R
import com.apptesting.test.adapter.HomePagerAdapter
import com.apptesting.test.bottomsheet.WebBottomSheet
import com.apptesting.test.databinding.ActivityMainBinding
import com.apptesting.test.fragments.CategoryFragment
import com.apptesting.test.fragments.FavouriteFragment
import com.apptesting.test.fragments.HomeFragment
import com.apptesting.test.utils.Const
import com.apptesting.test.utils.MyPlayStoreBilling
import com.apptesting.test.viewmodel.MainViewModel


class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var fragmentsList: ArrayList<Fragment>
    lateinit var viewModel: MainViewModel
    lateinit var webBottomSheet: WebBottomSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        initView()
        initisteners()

        initNavbarLIstners()
        initObserver()
        checkNotificationPermission()

        binding.model = viewModel


    }

    private val requestPermissionLauncher: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            Log.i(
                "TAG",
                ": $result"
            )
        }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun fetchSubscriptionDetails() {
        binding.navBar.loutLoader.visibility = View.VISIBLE

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
            Toast.makeText(
                this@MainActivity,
                getString(R.string.subscriptions_restored_successfully),
                Toast.LENGTH_SHORT
            ).show()
        } else {
            sessionManager.setPremium(false)
            Toast.makeText(
                this@MainActivity,
                getString(R.string.there_is_no_subscriptions),
                Toast.LENGTH_SHORT
            ).show()
        }


        binding.navBar.loutLoader.visibility = View.GONE


    }

    private fun initNavbarLIstners() {

        binding.navBar.btnRestore.setOnClickListener {
            fetchSubscriptionDetails()
        }

        binding.navBar.switchNoti.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                FirebaseMessaging.getInstance().subscribeToTopic(Const.NOTIFICATION_TOPIC)
                sessionManager.saveBooleanValue(Const.Key.is_notification, true)

            } else {
                FirebaseMessaging.getInstance().unsubscribeFromTopic(Const.NOTIFICATION_TOPIC)
                sessionManager.saveBooleanValue(Const.Key.is_notification, false)

            }
        }

        binding.navBar.btnRate.setOnClickListener { v ->
            closeDrawer()
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID)
                )
            )
        }



        binding.navBar.blurView.setOnClickListener {
            closeDrawer()
        }

        binding.navBar.btnPremium.setOnClickListener {
            closeDrawer()
            startActivity(Intent(this, PurchasePremiumActivity::class.java))
        }

        binding.navBar.btnPrivacy.setOnClickListener { v ->
            closeDrawer()
            webBottomSheet = WebBottomSheet(1)
            if (!webBottomSheet.isAdded) {
                webBottomSheet.show(
                    supportFragmentManager,
                    webBottomSheet.javaClass.simpleName
                )
            }
        }

        binding.navBar.btnTerms.setOnClickListener { v ->
            closeDrawer()
            webBottomSheet = WebBottomSheet(2)
            if (!webBottomSheet.isAdded) {
                webBottomSheet.show(
                    supportFragmentManager,
                    webBottomSheet.javaClass.simpleName
                )
            }
        }

    }

    private fun closeDrawer() {
        binding.navBar.blurView.visibility = View.GONE
        binding.drawerLout.closeDrawer(GravityCompat.START)

    }

    private fun initObserver() {
        viewModel.cordinated_expandeed.observe(this, Observer {
            if (it) {
                binding.logoImg.visibility = View.GONE
            } else {
                binding.logoImg.visibility = View.VISIBLE

            }
        })


        viewModel.selectedNav.observe(this) {

            binding.drawerLout.closeDrawer(GravityCompat.START)

            when (it) {


                1 -> {
//                    startActivity(Intent(this, StarActivity::class.java))
                }


            }

        }

        viewModel.selectedTab.observe(this) { it ->
            binding.vPager.currentItem = it

            removeBackgroundTint()
            when (it) {
                0 -> {

                    if (viewModel.cordinated_expandeed.value!!) {
                        binding.logoImg.visibility = View.GONE
                    } else {
                        binding.logoImg.visibility = View.VISIBLE

                    }


                    var value = 0
                    value = if (binding.parentHome.width == 0) {
                        300
                    } else {
                        binding.parentHome.width
                    }

                    val anim = ValueAnimator.ofInt(value)
                    anim.addUpdateListener { animator ->

                        val v = animator.animatedValue as Int

                        val layoutParams: ViewGroup.LayoutParams = binding.loutHome.layoutParams
                        layoutParams.width = v
                        layoutParams.height = MATCH_PARENT
                        binding.loutHome.layoutParams = layoutParams
                        binding.loutHome.backgroundTintList =
                            ContextCompat.getColorStateList(this, R.color.color_theme_blue)
                    }
                    anim.duration = 200
                    anim.start()

                }

                1 -> {

                    binding.logoImg.visibility = View.VISIBLE


                    val anim = ValueAnimator.ofInt(binding.parentCat.width)
                    anim.addUpdateListener { animator ->

                        val v = animator.animatedValue as Int

                        val layoutParams: ViewGroup.LayoutParams = binding.loutCat.layoutParams
                        layoutParams.width = v
                        layoutParams.height = MATCH_PARENT
                        binding.loutCat.layoutParams = layoutParams
                        binding.loutCat.backgroundTintList =
                            ContextCompat.getColorStateList(this, R.color.color_theme_blue)
                    }
                    anim.duration = 200
                    anim.start()

                }

                2 -> {

                    binding.logoImg.visibility = View.VISIBLE


                    val anim = ValueAnimator.ofInt(binding.parentFav.width)
                    anim.addUpdateListener { animator ->

                        val v = animator.animatedValue as Int
                        val layoutParams: ViewGroup.LayoutParams = binding.loutFav.layoutParams
                        layoutParams.width = v
                        layoutParams.height = MATCH_PARENT
                        binding.loutFav.layoutParams = layoutParams
                        binding.loutFav.backgroundTintList =
                            ContextCompat.getColorStateList(this, R.color.color_theme_blue)
                    }
                    anim.duration = 200
                    anim.start()

                }
            }

        }
    }


    override fun onBackPressed() {
        if (viewModel.navOpen.value!!) {
            viewModel.navOpen.value = false
        } else {

            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
    }


    private fun initisteners() {

        binding.loutLoader.setOnClickListener {

        }
        binding.drawerLout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                Log.i("TAG", "onDrawerSlide: $slideOffset")
                if (slideOffset < 1) {
                    binding.navBar.blurView.visibility = View.GONE

                }
                if (slideOffset > 0.95) {
                    binding.navBar.blurView.visibility = View.VISIBLE

                }
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
            }

            override fun onDrawerStateChanged(newState: Int) {
            }
        })
        viewModel.navOpen.observe(this) {
            if (it) {
                binding.drawerLout.openDrawer(GravityCompat.START)
                if (sessionManager.getPremium()) {
                    binding.navBar.loutPurchase.visibility = View.GONE
                    binding.navBar.btnRestore.visibility = View.GONE
                } else {
                    binding.navBar.loutPurchase.visibility = View.VISIBLE
                    binding.navBar.btnRestore.visibility = View.VISIBLE
                }

            } else {
                binding.drawerLout.closeDrawer(GravityCompat.START)

            }
        }

        binding.btnSearch.setOnClickListener(View.OnClickListener {

            startActivity(Intent(this, SearchActivity::class.java))
        })

    }

    private fun removeBackgroundTint() {


        val layoutParams1: ViewGroup.LayoutParams = binding.loutHome.layoutParams
        layoutParams1.width = 100

        binding.loutHome.layoutParams = layoutParams1
        binding.loutHome.backgroundTintList = null

        val layoutParams2: ViewGroup.LayoutParams = binding.loutCat.layoutParams
        layoutParams2.width = 100

        binding.loutCat.layoutParams = layoutParams2
        binding.loutCat.backgroundTintList = null

        val layoutParams3: ViewGroup.LayoutParams = binding.loutFav.layoutParams
        layoutParams3.width = 100

        binding.loutFav.layoutParams = layoutParams3
        binding.loutFav.backgroundTintList = null


    }

    private fun initView() {


        binding.vPager.isUserInputEnabled = false

        setBlur(binding.blurView, binding.rootLout)

        fragmentsList = ArrayList<Fragment>()

        fragmentsList.add(HomeFragment())
        fragmentsList.add(CategoryFragment())
        fragmentsList.add(FavouriteFragment())


        val adapter = HomePagerAdapter(
            supportFragmentManager,
            lifecycle
        )
        adapter.fragments = fragmentsList
        binding.vPager.adapter = adapter

        setBlur(binding.navBar.blurView, binding.rootLout)


        if (!sessionManager.getBooleanValue(Const.Key.is_old)) {
            FirebaseMessaging.getInstance().subscribeToTopic(Const.NOTIFICATION_TOPIC)
            sessionManager.saveBooleanValue(Const.Key.is_old, true)
            sessionManager.saveBooleanValue(Const.Key.is_notification, true)
        }

        binding.navBar.switchNoti.isChecked =
            sessionManager.getBooleanValue(Const.Key.is_notification)

        if (sessionManager.getPremium()) {
            binding.navBar.loutPurchase.visibility = View.GONE
            binding.navBar.btnRestore.visibility = View.GONE
        } else {
            binding.navBar.loutPurchase.visibility = View.VISIBLE
            binding.navBar.btnRestore.visibility = View.VISIBLE
        }

    }
}