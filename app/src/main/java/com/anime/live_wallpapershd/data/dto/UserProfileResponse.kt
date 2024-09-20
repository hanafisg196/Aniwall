package com.anime.live_wallpapershd.data.dto

import com.anime.live_wallpapershd.model.Posts
import com.anime.live_wallpapershd.model.User

data class UserProfileResponse (
    val user: User,
    val posts: Posts
)
