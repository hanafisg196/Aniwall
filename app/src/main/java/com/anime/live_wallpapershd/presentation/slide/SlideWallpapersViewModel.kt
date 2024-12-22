package com.anime.live_wallpapershd.presentation.slide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anime.live_wallpapershd.common.Constants
import com.anime.live_wallpapershd.data.paging.SlideWallpapersDataSource
import com.anime.live_wallpapershd.repository.SlideRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SlideWallpapersViewModel @Inject constructor(
    private val repository: SlideRepo
): ViewModel() {
    var slideId : Int = Constants.SLIDE_ID
    val wallpapersSlidePager = Pager(
        PagingConfig(pageSize = Constants.ITEM_PAGE)
    ){
        SlideWallpapersDataSource(repository,slideId)
    }
        .flow.cachedIn(viewModelScope)

}