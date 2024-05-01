package com.anisuki.animewallpapers.data

import com.anisuki.animewallpapers.common.Constants.API_KEY
import com.anisuki.animewallpapers.common.Constants.APP_ID
import com.anisuki.animewallpapers.data.dto.RandomResponse
import com.anisuki.animewallpapers.data.dto.SlideResponse
import com.anisuki.animewallpapers.data.dto.WallpaperResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers(
        "ApiKey:${API_KEY}",
        "AppId:${APP_ID}"
    )
    @GET("wallpaper/random")
    suspend fun getRandom(): RandomResponse

    @Headers(
        "ApiKey:${API_KEY}",
        "AppId:${APP_ID}"
    )
    @GET("wallpaper/slide")
    suspend fun getSlide(): SlideResponse

    @Headers(
        "ApiKey:${API_KEY}",
        "AppId:${APP_ID}"
    )
    @GET("wallpaper/detail/{id}")
    suspend fun getWallpaper(@Path("id") id: Int): WallpaperResponse

}