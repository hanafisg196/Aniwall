package com.apptesting.test.viewmodel

import androidx.lifecycle.MutableLiveData
import com.apptesting.test.adapter.CategoryAdapter


open class CategoryViewModel : BaseViewModel() {


    lateinit var categoryAdapter: CategoryAdapter
    var selected: MutableLiveData<Int> = MutableLiveData(0)


    public fun setSelectedType(i: Int) {
        selected.value = i
    }
}