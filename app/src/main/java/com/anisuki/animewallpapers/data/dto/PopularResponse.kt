package com.anisuki.animewallpapers.data.dto

import com.anisuki.animewallpapers.model.Popular
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