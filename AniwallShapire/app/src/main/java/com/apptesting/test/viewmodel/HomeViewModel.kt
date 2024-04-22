package com.apptesting.test.viewmodel

import com.apptesting.test.adapter.FeatureDotsAdapter
import com.apptesting.test.adapter.FeatureImagesAdapter
import com.apptesting.test.adapter.HomeCatAdapter
import com.apptesting.test.adapter.HomeImagesByCatAdapter


open class HomeViewModel : BaseViewModel() {


    var featureImagesAdapter = FeatureImagesAdapter()
    var featureDotsAdapter = FeatureDotsAdapter()
    var homeCatAdapter = HomeCatAdapter()
    var homeImagesByCatAdapter = HomeImagesByCatAdapter()

}