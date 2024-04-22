package com.anisuki.animewallpapers.data.remote

import com.anisuki.animewallpapers.data.remote.dto.RandomResponse
import com.anisuki.animewallpapers.common.Constants.API_KEY
import com.anisuki.animewallpapers.common.Constants.APP_ID
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {
    @Headers(
        "api_key: $API_KEY",
        "X-Android-PackageName: $APP_ID"
    )
    @GET("wallpaper/random")
    suspend fun getRandom(): RandomResponse
}