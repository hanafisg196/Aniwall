package com.anime.live_wallpapershd.model

import com.google.gson.annotations.SerializedName

data class Wallpapers(
    val id: Int,
    val thumbnail: String,
    val title: String,
    val type: String,
    val view: Int,
    val review: Int,
    val users: User,
    val category_name:String
)

