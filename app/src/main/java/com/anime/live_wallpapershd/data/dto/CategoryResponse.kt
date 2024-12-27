package com.anime.live_wallpapershd.data.dto

import com.anime.live_wallpapershd.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data")
    val data : Category
)
