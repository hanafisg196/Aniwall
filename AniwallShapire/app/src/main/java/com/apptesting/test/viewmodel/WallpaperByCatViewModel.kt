package com.apptesting.test.viewmodel

import com.apptesting.test.adapter.HomeImagesByCatAdapter
import com.apptesting.test.model.AllData


open class WallpaperByCatViewModel : BaseViewModel() {

    var category: AllData.CategoriesItem? = null

    var homeImagesByCatAdapter = HomeImagesByCatAdapter()


}