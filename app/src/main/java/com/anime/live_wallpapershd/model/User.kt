package com.anime.live_wallpapershd.model

data class User(
    val id: Int,
    val email: String,
    val name: String,
    val avatar: String,
    val token: String,
    val posts:Int
)

