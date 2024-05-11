package com.anisuki.animewallpapers.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anisuki.animewallpapers.common.Constants
import com.anisuki.animewallpapers.data.paging.CategoriesDataResource
import com.anisuki.animewallpapers.repository.CategoriesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesVieModel @Inject constructor(
    private val repository: CategoriesRepo
):ViewModel() {
    val categoriesPager = Pager(
        PagingConfig(pageSize = Constants.ITEM_PAGE)
    ){
        CategoriesDataResource(repository)
    }.flow.cachedIn(viewModelScope)

}