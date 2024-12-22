package com.anime.live_wallpapershd.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anime.live_wallpapershd.common.Constants
import com.anime.live_wallpapershd.common.Constants.CATEGORY_ID
import com.anime.live_wallpapershd.data.paging.WallpapersByCatDataSource
import com.anime.live_wallpapershd.repository.WallpapersByCatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WallpapersByCatViewModel @Inject constructor(
    private val repository : WallpapersByCatRepo,
):ViewModel() {
    var catId: Int = CATEGORY_ID
    val wallpapersByCatPager = Pager(
        PagingConfig(pageSize = Constants.ITEM_PAGE)
    ){
        WallpapersByCatDataSource(repository,catId)
    }
    .flow.cachedIn(viewModelScope)
}