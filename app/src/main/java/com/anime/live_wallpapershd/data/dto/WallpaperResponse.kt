package com.anime.live_wallpapershd.data.dto

import com.anime.live_wallpapershd.model.Wallpaper
import com.google.gson.annotations.SerializedName

data class WallpaperResponse(
@SerializedName("data")
    val data: Wallpaper
)