package com.apptesting.test.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apptesting.test.R
import com.apptesting.test.databinding.ActivitySearchBinding
import com.apptesting.test.databinding.ItemFilterPopupBinding
import com.apptesting.test.model.AllData
import com.apptesting.test.utils.Const
import com.apptesting.test.utils.Global
import com.apptesting.test.viewmodel.SearchViewModel

class SearchActivity : BaseActivity() {

    lateinit var binding: ActivitySearchBinding
    lateinit var model: SearchViewModel

    lateinit var dialogFilter: Dialog
    var allList: MutableList<AllData.WallpapersItem> = arrayListOf()
    var lockedList: MutableList<AllData.WallpapersItem> = arrayListOf()
    var premiumList: MutableList<AllData.WallpapersItem> = arrayListOf()
    var searchList: MutableList<AllData.WallpapersItem> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        model = ViewModelProvider(this)[SearchViewModel::class.java]


        initView()
        initListeners()
        initObserver()
        binding.model = model

    }

    private fun initObserver() {
        model.filterType.observe(this, Observer {
            when (it) {
                2 -> {
                    searchFromAllWallpapers()
                }

                1 -> {
                    searchFromLockedWallpapers()
                }

                0 -> {
                    searchFromPremiumWallpapers()
                }
            }
        })
    }

    private fun showFilterDialogue() {
        dialogFilter = Dialog(this)
        dialogFilter.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        var binding: ItemFilterPopupBinding
        val view = LayoutInflater.from(this)
            .inflate(R.layout.item_filter_popup, null, false)
        binding = DataBindingUtil.bind(view)!!


        binding.btnAll.setOnClickListener {
            model.filterType.value = 2
            dialogFilter.dismiss()
        }
        binding.btnLocked.setOnClickListener {
            model.filterType.value = 1

            dialogFilter.dismiss()

        }

        binding.btnPremium.setOnClickListener {
            model.filterType.value = 0

            dialogFilter.dismiss()

        }
        dialogFilter.setContentView(view)
        dialogFilter.setCancelable(true)
        dialogFilter.show()
    }


    private fun initListeners() {

        binding.btnFilter.setOnClickListener {
            showFilterDialogue()
        }

        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()

        }


        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                model.searchWord = editable.toString()
                binding.tvSearchWord.text = "\"" + model.searchWord + "\"";

                when (model.filterType.value) {
                    2 -> {
                        searchFromAllWallpapers()
                    }

                    1 -> {
                        searchFromLockedWallpapers()
                    }

                    0 -> {
                        searchFromPremiumWallpapers()
                    }
                }
            }
        })


    }


    private fun refreshFavList() {

        model.homeImagesByCatAdapter.favList =
            Global.convertStringToLis(sessionManager.getStringValue(Const.Key.favourites))
                .toMutableList()
        model.homeImagesByCatAdapter.notifyDataSetChanged()

        Log.i(" onnnn set search", ": ${model.homeImagesByCatAdapter.favList}")


    }

    override fun onResume() {
        refreshFavList()
        super.onResume()

    }

    private fun searchFromAllWallpapers() {

        if (model.searchWord.isEmpty()) {
            model.noData.set(false)
            model.homeImagesByCatAdapter.updateData(allList)
            return
        }
        searchList = mutableListOf()
        for (item: AllData.WallpapersItem in allList) {
            if (item.tags.toString().lowercase()
                    .contains(model.searchWord.lowercase())
            ) {

                searchList.add(item)
            }
        }


        model.homeImagesByCatAdapter.updateData(searchList)
        if (searchList.isEmpty()) {

            model.noData.set(true)
        } else {
            model.noData.set(false)

        }

        binding.nestedScrollView.smoothScrollTo(0, 0)

    }

    private fun searchFromLockedWallpapers() {

        if (model.searchWord.isEmpty()) {
            model.noData.set(false)
            model.homeImagesByCatAdapter.updateData(lockedList)
            return
        }
        searchList = mutableListOf()
        for (item: AllData.WallpapersItem in lockedList) {
            if (item.tags.toString().lowercase()
                    .contains(model.searchWord.lowercase())
            ) {

                searchList.add(item)
            }
        }


        model.homeImagesByCatAdapter.updateData(searchList)
        if (searchList.isEmpty()) {

            model.noData.set(true)
        } else {
            model.noData.set(false)

        }
        binding.nestedScrollView.smoothScrollTo(0, 0)

    }

    private fun searchFromPremiumWallpapers() {

        if (model.searchWord.isEmpty()) {
            model.noData.set(false)
            model.homeImagesByCatAdapter.updateData(premiumList)
            return
        }
        searchList = mutableListOf()
        for (item: AllData.WallpapersItem in premiumList) {
            if (item.tags.toString().lowercase()
                    .contains(model.searchWord.lowercase())
            ) {

                searchList.add(item)
            }
        }


        model.homeImagesByCatAdapter.updateData(searchList)
        if (searchList.isEmpty()) {

            model.noData.set(true)
        } else {
            model.noData.set(false)

        }
        binding.nestedScrollView.smoothScrollTo(0, 0)

    }


    private fun initView() {

        allList = (Global.allWallpapers as MutableList<AllData.WallpapersItem>?)!!

        lockedList = Global.getLockedList()
        premiumList = Global.getPremiumList()
        model.homeImagesByCatAdapter.favList =
            Global.convertStringToLis(sessionManager.getStringValue(Const.Key.favourites))
                .toMutableList()
        model.homeImagesByCatAdapter.updateData(allList)

    }
}