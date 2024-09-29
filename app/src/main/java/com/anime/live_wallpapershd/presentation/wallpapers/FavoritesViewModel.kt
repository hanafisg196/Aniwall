package com.anime.live_wallpapershd.presentation.wallpapers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anime.live_wallpapershd.common.Constants.ITEM_PAGE
import com.anime.live_wallpapershd.data.paging.FavoriteDataResource
import com.anime.live_wallpapershd.repository.FavoriteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: FavoriteRepo
):ViewModel() {
    val favoritesPager = Pager(
        PagingConfig(pageSize = ITEM_PAGE)
    ) {
        FavoriteDataResource(repository)
    }.flow.cachedIn(viewModelScope)

}