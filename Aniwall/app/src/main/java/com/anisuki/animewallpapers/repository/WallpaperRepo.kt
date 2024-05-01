package com.anisuki.animewallpapers.repository

import com.anisuki.animewallpapers.data.ApiService
import com.anisuki.animewallpapers.model.Wallpaper
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