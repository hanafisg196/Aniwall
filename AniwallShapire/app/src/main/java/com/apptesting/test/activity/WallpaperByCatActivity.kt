package com.apptesting.test.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.apptesting.test.R
import com.apptesting.test.databinding.ActivityWallpaperByCatBinding
import com.apptesting.test.model.AllData
import com.apptesting.test.utils.Const
import com.apptesting.test.utils.Global
import com.apptesting.test.viewmodel.WallpaperByCatViewModel

class WallpaperByCatActivity : BaseActivity() {

    lateinit var binding: ActivityWallpaperByCatBinding

    var category: AllData.CategoriesItem? = null
    lateinit var model: WallpaperByCatViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallpaper_by_cat)
        model = ViewModelProvider(this)[WallpaperByCatViewModel::class.java]

        initView()
        initListeners()
        binding.model = model
    }

    private fun initListeners() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun refreshFavList() {

        model.homeImagesByCatAdapter.favList =
            Global.convertStringToLis(sessionManager.getStringValue(Const.Key.favourites))
                .toMutableList()
        model.homeImagesByCatAdapter.notifyDataSetChanged()

        Log.i(" onnnn set wall by cat", ": ${model.homeImagesByCatAdapter.favList}")


    }

    override fun onResume() {
        refreshFavList()

        super.onResume()
    }

    private fun initView() {


        var s = intent.getStringExtra(Const.Key.data)
        if (s != null) {
            category = Gson().fromJson(s, AllData.CategoriesItem::class.java)
            model.category = category
        } else {
            binding.tvNoData.visibility = View.VISIBLE
        }

        category?.let {

            model.homeImagesByCatAdapter.favList =
                Global.convertStringToLis(sessionManager.getStringValue(Const.Key.favourites))
                    .toMutableList()
            model.homeImagesByCatAdapter.updateData(it.wallpapers!!)
            if (it.wallpapers!!.isEmpty()) {
                binding.tvNoData.visibility = View.VISIBLE

            }
        }
    }
}