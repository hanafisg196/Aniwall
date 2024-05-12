package com.anime.live_wallpapershd.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anime.live_wallpapershd.common.Constants
import com.anime.live_wallpapershd.data.paging.CategoriesDataResource
import com.anime.live_wallpapershd.repository.CategoriesRepo
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