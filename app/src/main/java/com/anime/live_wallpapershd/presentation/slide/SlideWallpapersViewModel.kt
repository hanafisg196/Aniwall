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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideWallpapersViewModel @Inject constructor(
    private val repository: SlideRepo
): ViewModel() {
    var slideId : Int = Constants.SLIDE_ID
    private val _slideName = MutableStateFlow<String?>(null)
    val slideNameState: StateFlow<String?> get() = _slideName

    fun getSlideDetail(){
        viewModelScope.launch {
            val name =  repository.getSlideDetail(slideId)
            _slideName.value = name.data.name
        }
    }

    val wallpapersSlidePager = Pager(
        PagingConfig(pageSize = Constants.ITEM_PAGE)
    ){
        SlideWallpapersDataSource(repository,slideId)
    }
        .flow.cachedIn(viewModelScope)



}