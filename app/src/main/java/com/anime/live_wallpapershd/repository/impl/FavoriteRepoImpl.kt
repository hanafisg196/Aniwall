package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.CheckFavoriteResponse
import com.anime.live_wallpapershd.data.dto.FavoriteResponse
import com.anime.live_wallpapershd.data.dto.FavoriteWallpapersResponse
import com.anime.live_wallpapershd.data.dto.request.FavoriteRequest
import com.anime.live_wallpapershd.repository.FavoriteRepo
import retrofit2.Response
import javax.inject.Inject

class FavoriteRepoImpl @Inject constructor(
    private val apiService: ApiService
):FavoriteRepo {
    override suspend fun addFavorite(token: String, wallpaperId: Int): Response<FavoriteResponse> {
        val request = FavoriteRequest(wallpaperId)
        return apiService.addFavorite(token, request)
    }
    override suspend fun removeFavorite(token: String, wallpaperId: Int): Response<FavoriteResponse> {
        val request = FavoriteRequest(wallpaperId)
        return apiService.removeFavorite(token, request)
    }
    override suspend fun checkFavorite(token: String, wallpaperId: Int):CheckFavoriteResponse{
        val response = apiService.checkFavorite(token, wallpaperId)
        return response
    }
    override suspend fun favoriteWallpapers(token: String,userId:Int, page:Int, perPage:Int):FavoriteWallpapersResponse
    {
        return  apiService.favoriteWallpapers(token,userId,page,perPage)
    }


}