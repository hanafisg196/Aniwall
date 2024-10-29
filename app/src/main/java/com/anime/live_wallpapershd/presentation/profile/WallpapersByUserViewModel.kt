package com.anime.live_wallpapershd.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anime.live_wallpapershd.common.Constants.ITEM_PAGE
import com.anime.live_wallpapershd.data.paging.WallpaperByUserDataSource
import com.anime.live_wallpapershd.repository.WallpapersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WallpapersByUserViewModel @Inject constructor(
    private val wallpapersRepo: WallpapersRepo
):ViewModel() {
    val wallpapersByUserPager = Pager(
        PagingConfig(pageSize = ITEM_PAGE)
    )
    {
      WallpaperByUserDataSource(wallpapersRepo)
    }.flow.cachedIn(viewModelScope)

}