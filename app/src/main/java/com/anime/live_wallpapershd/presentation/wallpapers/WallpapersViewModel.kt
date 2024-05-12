package com.anime.live_wallpapershd.presentation.wallpapers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anime.live_wallpapershd.common.Constants.ITEM_PAGE
import com.anime.live_wallpapershd.data.paging.LatestDataSource
import com.anime.live_wallpapershd.repository.WallpapersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(
    private  val  repository: WallpapersRepo
):ViewModel() {
        val wallpaperPager = Pager(
            PagingConfig(pageSize = ITEM_PAGE)
        ){
            LatestDataSource(repository)
        }.flow.cachedIn(viewModelScope)
}