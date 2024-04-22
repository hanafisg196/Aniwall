package com.apptesting.test.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.apptesting.test.R
import com.apptesting.test.databinding.FragmentFavouriteBinding
import com.apptesting.test.utils.Const
import com.apptesting.test.utils.Global
import com.apptesting.test.viewmodel.FavouriteViewModel

class FavouriteFragment : BaseFragment() {

    lateinit var binding: FragmentFavouriteBinding
    lateinit var model: FavouriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)
        model = ViewModelProvider(requireActivity())[FavouriteViewModel::class.java]



        doOnResume()
        binding.model = model
        return binding.root
    }

    private fun doOnResume() {
        var s = sessionManager.getStringValue(Const.Key.favourites)

        var list = Global.convertStringToLis(s)
        Log.i("TAG", "initView: $s")
        Log.i("TAG", "initView: $list")


        if (list.isEmpty()) {
            binding.loutNoFav.visibility = View.VISIBLE
        } else {

            binding.loutNoFav.visibility = View.GONE
        }

        model.homeImagesByCatAdapter.favList =
            Global.convertStringToLis(sessionManager.getStringValue(Const.Key.favourites))
                .toMutableList()
        model.homeImagesByCatAdapter.updateData(Global.getFavWallpaper(list))

        Log.i(" onnnn set fav", ": ${model.homeImagesByCatAdapter.favList}")

    }

    override fun onResume() {
        super.onResume()
        doOnResume()
    }

    private fun refreshFavList() {

        doOnResume()


    }

}