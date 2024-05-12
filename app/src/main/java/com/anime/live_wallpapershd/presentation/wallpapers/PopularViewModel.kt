package com.anime.live_wallpapershd.presentation.wallpapers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anime.live_wallpapershd.common.Constants.ITEM_PAGE
import com.anime.live_wallpapershd.data.paging.PopularDataSource
import com.anime.live_wallpapershd.repository.PopularRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: PopularRepo
):ViewModel() {
    val popularPager = Pager(
        PagingConfig(pageSize = ITEM_PAGE)
    ){
        PopularDataSource(repository)
    }.flow.cachedIn(viewModelScope)
}