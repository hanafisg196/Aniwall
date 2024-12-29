package com.anime.live_wallpapershd.presentation.wallpapers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anime.live_wallpapershd.common.Constants.ITEM_PAGE
import com.anime.live_wallpapershd.common.Constants.QUERY
import com.anime.live_wallpapershd.data.paging.SearchWallpapersDataResource
import com.anime.live_wallpapershd.repository.WallpapersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: WallpapersRepo
):ViewModel() {
    private var _searchQuery = MutableStateFlow(QUERY)
    val searchQuery: StateFlow<String> get() = _searchQuery

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _searchPage = _searchQuery.flatMapLatest { query ->
        Pager(
            PagingConfig(pageSize = ITEM_PAGE)
        ) {
            SearchWallpapersDataResource(repository, query)
        }.flow.cachedIn(viewModelScope)
    }
    val searchPage = _searchPage

    fun updateSearchQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

}