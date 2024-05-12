package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.model.Wallpaper
import javax.inject.Inject

class WallpaperRepo @Inject constructor(
    private val api:ApiService
)
    {
    suspend fun getWallpaperById(id: Int) : Wallpaper
    {
           val wallpaperResponse = api.getWallpaper(id)
           return  wallpaperResponse.data
    }


}