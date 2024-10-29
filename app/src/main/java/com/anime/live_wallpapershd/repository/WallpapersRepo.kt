package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.WallpapersResponse

interface WallpapersRepo {
    suspend fun getWallpapers(page: Int, perPage: Int)
    : WallpapersResponse

    suspend fun getWallpapersByUser(token:String,userId: Int,page: Int, perPage: Int)
    :WallpapersResponse
}