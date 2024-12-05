package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.WallpapersOwnerResponse
import com.anime.live_wallpapershd.data.dto.WallpapersResponse
import com.anime.live_wallpapershd.model.User

interface WallpapersRepo {
    suspend fun getWallpapers(page: Int, perPage: Int)
    : WallpapersResponse
    suspend fun getWallpapersByUser(token:String,userId: Int,page: Int, perPage: Int)
    :WallpapersResponse
    suspend fun getWallpapersUserDetail(userId: Int,page: Int, perPage: Int)
    :WallpapersResponse
    suspend fun  getWallpaperOwner(userId: Int)
    : WallpapersOwnerResponse

}