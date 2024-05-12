package com.anime.live_wallpapershd.data.dto

import com.anime.live_wallpapershd.model.Popular
import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("data")
    val data : List<Popular>,
    @SerializedName("page")
    val page : Int,
    @SerializedName("per_page")
    val perPage : Int,
    @SerializedName("total")
    val total : Int,
)