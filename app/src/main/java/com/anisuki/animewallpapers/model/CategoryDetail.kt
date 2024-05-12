package com.anisuki.animewallpapers.model

import com.google.gson.annotations.SerializedName

data class CategoryDetail(
    @SerializedName("id")
    val id : Int,
    @SerializedName("wallpapers")
    val wallpapers : List<Wallpapers>
)
