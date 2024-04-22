package com.apptesting.test.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MediatorLiveData
import com.apptesting.test.adapter.HomeImagesByCatAdapter


open class SearchViewModel : BaseViewModel() {


    var homeImagesByCatAdapter = HomeImagesByCatAdapter()
    var noData = ObservableBoolean(false)

    var searchWord = ""

    var filterType = MediatorLiveData(2)

}