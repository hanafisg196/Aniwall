package com.anime.live_wallpapershd.data.dto


import com.anime.live_wallpapershd.model.Slide
import com.google.gson.annotations.SerializedName

data class SlideResponse(
    @SerializedName("data")
    val data: List<Slide>
)