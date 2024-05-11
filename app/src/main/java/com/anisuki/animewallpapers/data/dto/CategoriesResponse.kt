package com.anisuki.animewallpapers.data.dto

import com.anisuki.animewallpapers.model.Categories
import com.anisuki.animewallpapers.model.Wallpapers
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