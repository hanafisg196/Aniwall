package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.NotificationTokenResponse
import retrofit2.Response

interface NotificationRepo {
    suspend fun sendToken (deviceToken : String): Response<NotificationTokenResponse>
}