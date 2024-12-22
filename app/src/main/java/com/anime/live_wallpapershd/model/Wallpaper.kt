package com.anime.live_wallpapershd.model

data class Wallpaper(
    val cat_id: Int,
    val download: Int,
    val enabled: Int,
    val id: Int,
    val premium: Int,
    val resolution: String,
    val review: Int,
    val size: String,
    val title: String,
    val type: String,
    val user_id: Int,
    val view: Int,
    val users: User,
    val category: Category
)