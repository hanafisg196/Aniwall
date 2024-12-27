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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WallpapersByCatViewModel @Inject constructor(
    private val repository : WallpapersByCatRepo,
):ViewModel() {
    var catId: Int = CATEGORY_ID
    private val _categoryName = MutableStateFlow<String?>(null)
    val categoryNameState: StateFlow<String?> get() = _categoryName

      fun getWallpaperCatName() {
        viewModelScope.launch {
           val name = repository.getWallpapersCatName(catId)
            _categoryName.value = name.data.name
        }
    }
    val wallpapersByCatPager = Pager(
        PagingConfig(pageSize = Constants.ITEM_PAGE)
    ){
        WallpapersByCatDataSource(repository,catId)
    }
    .flow.cachedIn(viewModelScope)
}