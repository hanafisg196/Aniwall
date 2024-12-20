package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.MessageResponse
import com.anime.live_wallpapershd.repository.ReportRepo
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject

class ReportRepoImpl @Inject constructor(
    private val apiService: ApiService
): ReportRepo {
    override suspend fun sendReportWallpaper(
        wallpaperId: Int,
        email: String,
        description: String
    ): Response<MessageResponse> {
        val emailRequest = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionRequest = description.toRequestBody("text/plain".toMediaTypeOrNull())
        return apiService.sendReportWallpaper(wallpaperId, emailRequest, descriptionRequest)
    }

    override  suspend fun  sendReportUser(
        userId: Int,
        email: String,
        description: String
    ):Response<MessageResponse> {
        val emailRequest = email.toRequestBody("text/plain".toMediaTypeOrNull())
        val descriptionRequest = description.toRequestBody("text/plain".toMediaTypeOrNull())
        return apiService.sendReportUser(userId, emailRequest, descriptionRequest)
    }

}