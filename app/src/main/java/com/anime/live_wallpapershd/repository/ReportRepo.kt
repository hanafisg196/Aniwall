package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.MessageResponse
import retrofit2.Response

interface ReportRepo {
    suspend fun sendReportWallpaper(
        wallpaperId: Int,
        email: String,
        description: String
    ): Response<MessageResponse>

    suspend fun sendReportUser(
        userId:Int,
        email: String,
        description: String
    ):Response<MessageResponse>
}