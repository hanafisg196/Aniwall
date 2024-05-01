package com.anisuki.animewallpapers.data.dto

import com.anisuki.animewallpapers.model.Random
import com.google.gson.annotations.SerializedName

data class RandomResponse(
    @SerializedName("data")
    val data: List<Random>
)
