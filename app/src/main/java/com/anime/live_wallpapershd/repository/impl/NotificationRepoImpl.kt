package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.NotificationTokenResponse
import com.anime.live_wallpapershd.data.dto.request.NotificationTokenRequest
import com.anime.live_wallpapershd.repository.NotificationRepo
import retrofit2.Response
import javax.inject.Inject

class NotificationRepoImpl @Inject constructor(
    private val apiService: ApiService

):NotificationRepo {
    override suspend fun sendToken(deviceToken: String): Response<NotificationTokenResponse> {
       val  request = NotificationTokenRequest(deviceToken)
        return apiService.sendToken(request)
    }

}