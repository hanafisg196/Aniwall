package com.anisuki.animewallpapers.presentation.categories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anisuki.animewallpapers.common.Constants
import com.anisuki.animewallpapers.data.paging.WallpapersByCatDataSource
import com.anisuki.animewallpapers.repository.WallpapersByCatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WallpapersByCatViewModel @Inject constructor(

    private val repository : WallpapersByCatRepo,

):ViewModel() {

    val wallpapersByCatPager = Pager(
        PagingConfig(pageSize = Constants.ITEM_PAGE)
    ){
        WallpapersByCatDataSource(repository)
    }.flow.cachedIn(viewModelScope)
}