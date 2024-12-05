package com.anime.live_wallpapershd.repository
import com.anime.live_wallpapershd.data.dto.UploadWallpaperResponse
import okhttp3.MultipartBody
import retrofit2.Response

interface UploadWallpaperRepo {
    suspend fun uploadWallpaper(
        token: String,
        title: String,
        catId: Int,
        type: MultipartBody.Part
    ): Response<UploadWallpaperResponse>
}