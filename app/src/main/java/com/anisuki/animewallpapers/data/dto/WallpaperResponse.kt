package com.anisuki.animewallpapers.data.dto

import com.anisuki.animewallpapers.model.Wallpaper
import com.google.gson.annotations.SerializedName

data class WallpaperResponse(
@SerializedName("data")
    val data: Wallpaper
)