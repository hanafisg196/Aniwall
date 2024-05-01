package com.anisuki.animewallpapers.data.dto


import com.anisuki.animewallpapers.model.Slide
import com.google.gson.annotations.SerializedName

data class SlideResponse(
    @SerializedName("data")
    val data: List<Slide>
)