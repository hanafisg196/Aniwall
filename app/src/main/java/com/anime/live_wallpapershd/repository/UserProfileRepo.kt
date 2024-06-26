package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.model.User
import javax.inject.Inject

class UserProfileRepo @Inject constructor(
    private val api:ApiService
) {
    suspend fun getUserProfile(token:String):User
    {
       val userResponse = api.getUserProfile(token)
        return userResponse.user
    }
}