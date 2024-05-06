package com.anisuki.animewallpapers.data.dto

import com.anisuki.animewallpapers.model.Wallpapers
import com.google.gson.annotations.SerializedName

data class WallpapersResponse(
    @SerializedName("data")
    val data : List<Wallpapers>,
    @SerializedName("page")
    val page : Int,
    @SerializedName("per_page")
    val perPage : Int,
    @SerializedName("total")
    val total : Int,
)