package com.anime.live_wallpapershd.data.dto

import com.anime.live_wallpapershd.model.Random
import com.google.gson.annotations.SerializedName

data class RandomResponse(
    @SerializedName("data")
    val data: List<Random>
)
