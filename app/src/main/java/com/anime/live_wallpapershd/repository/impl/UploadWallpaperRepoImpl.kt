package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.UploadWallpaperResponse
import com.anime.live_wallpapershd.repository.UploadWallpaperRepo
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject

class UploadWallpaperRepoImpl @Inject constructor(
    private val api: ApiService
):UploadWallpaperRepo {
    override suspend fun uploadWallpaper(
        token: String,
        title: String,
        catId: Int,
        type: MultipartBody.Part
    ): Response<UploadWallpaperResponse> {
        val titleRequestBody = title.toRequestBody("text/plain".toMediaTypeOrNull())
        val catIdRequestBody = catId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        return api.uploadWallpaper(token,titleRequestBody,catIdRequestBody,type)
    }


}