package com.anime.live_wallpapershd.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anime.live_wallpapershd.model.Wallpaper
import com.anime.live_wallpapershd.repository.WallpaperRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val wallpaperRepo: WallpaperRepo
) : ViewModel() {
    private val _wallpaper = MutableStateFlow<Wallpaper?>(null)
    val state: StateFlow<Wallpaper?>  get()= _wallpaper

    fun getWallpaper(id: Int) {
        viewModelScope.launch {
            _wallpaper.value = wallpaperRepo.getWallpaperById(id)
        }
    }

}

