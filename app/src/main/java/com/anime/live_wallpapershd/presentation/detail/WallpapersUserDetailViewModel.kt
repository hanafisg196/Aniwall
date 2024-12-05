package com.anime.live_wallpapershd.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anime.live_wallpapershd.common.Constants
import com.anime.live_wallpapershd.common.Constants.ID
import com.anime.live_wallpapershd.common.Constants.OWNER_ID
import com.anime.live_wallpapershd.data.paging.WallpapersUserDetailDataSource
import com.anime.live_wallpapershd.model.User
import com.anime.live_wallpapershd.repository.WallpapersRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpapersUserDetailViewModel @Inject constructor(
    private val repository: WallpapersRepo,
): ViewModel() {
    private val _wallpaperOwner = MutableStateFlow<User?>(null)
    val ownerState: StateFlow<User?> = _wallpaperOwner
    var ownerId: Int = OWNER_ID
    fun getWallpaperOwner() {
        viewModelScope.launch {
            val owner = repository.getWallpaperOwner(ownerId)
            _wallpaperOwner.value = owner.data
        }
    }
    val wallpaperPager = Pager(
        config = PagingConfig(pageSize = Constants.ITEM_PAGE)
    ){
        WallpapersUserDetailDataSource(repository,ownerId)
    }.flow.cachedIn(viewModelScope)

}