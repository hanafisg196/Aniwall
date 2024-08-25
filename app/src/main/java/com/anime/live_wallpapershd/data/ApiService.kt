package com.anime.live_wallpapershd.data

import com.anime.live_wallpapershd.common.Constants.API_KEY
import com.anime.live_wallpapershd.common.Constants.APP_ID
import com.anime.live_wallpapershd.data.dto.CategoriesResponse
import com.anime.live_wallpapershd.data.dto.GoogleSignInResponse
import com.anime.live_wallpapershd.data.dto.PopularResponse
import com.anime.live_wallpapershd.data.dto.RandomResponse
import com.anime.live_wallpapershd.data.dto.SingInRequest
import com.anime.live_wallpapershd.data.dto.SlideResponse
import com.anime.live_wallpapershd.data.dto.UserProfileResponse
import com.anime.live_wallpapershd.data.dto.WallpaperResponse
import com.anime.live_wallpapershd.data.dto.WallpapersByCatResponse
import com.anime.live_wallpapershd.data.dto.WallpapersResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @Headers(
        "ApiKey:${API_KEY}",
        "AppId:${APP_ID}"
    )
    @GET("wallpaper/latest")
    suspend fun getLatest(
        @Query("page") page: Int ,
        @Query("perPage") perPage: Int
    ):WallpapersResponse

    @Headers(
        "ApiKey:${API_KEY}",
        "AppId:${APP_ID}"
    )
    @GET("wallpaper/popular")
    suspend fun getPopular(
        @Query("page") page: Int ,
        @Query("perPage") perPage: Int
    ):PopularResponse
    @Headers(
        "ApiKey:${API_KEY}",
        "AppId:${APP_ID}"
    )
    @GET("wallpaper/categories")
    suspend fun getCategories(
        @Query("page") page: Int ,
        @Query("perPage") perPage: Int
    ):CategoriesResponse
    @Headers(
        "ApiKey:${API_KEY}",
        "AppId:${APP_ID}"
    )
    @GET("wallpaper/wallpapersByCat/{id}")
    suspend fun getWallpapersByCat(
        @Path("id") id: Int,
        @Query("page") page: Int ,
        @Query("perPage") perPage: Int
    ):WallpapersByCatResponse

    @Headers(
        "ApiKey:${API_KEY}",
        "AppId:${APP_ID}"
    )
    @POST("wallpaper/googlesignin")
    fun googleSignIn(
        @Body request: SingInRequest
    ): Response<GoogleSignInResponse>

    @Headers(
        "ApiKey:${API_KEY}",
        "AppId:${APP_ID}"
    )
    @GET("wallpaper/userprofile")
    suspend fun getUserProfile(
        @Header("Authorization") authHeader: String
    ):UserProfileResponse


}