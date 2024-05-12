package com.anime.live_wallpapershd.data.dto

import com.anime.live_wallpapershd.model.Categories
import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("data")
    val data : List<Categories>,
    @SerializedName("page")
    val page : Int,
    @SerializedName("per_page")
    val perPage : Int,
    @SerializedName("total")
    val total : Int,
)