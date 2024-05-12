package com.anime.live_wallpapershd.data.dto

import com.anime.live_wallpapershd.model.Wallpapers
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