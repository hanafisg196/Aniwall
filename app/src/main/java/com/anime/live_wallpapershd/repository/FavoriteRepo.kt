package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.CheckFavoriteResponse
import com.anime.live_wallpapershd.data.dto.FavoriteResponse
import com.anime.live_wallpapershd.data.dto.FavoriteWallpapersResponse
import retrofit2.Response


interface FavoriteRepo {
    suspend fun addFavorite(token: String, wallpaperId: Int): Response<FavoriteResponse>
    suspend fun removeFavorite(token: String, wallpaperId: Int): Response<FavoriteResponse>
    suspend fun checkFavorite(token: String, wallpaperId: Int):CheckFavoriteResponse
    suspend fun favoriteWallpapers(
        token: String,userId:Int, page:Int, perPage:Int
    ):FavoriteWallpapersResponse
}