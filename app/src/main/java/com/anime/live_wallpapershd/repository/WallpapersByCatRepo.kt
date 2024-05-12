package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.WallpapersByCatResponse

interface WallpapersByCatRepo {
    suspend fun getWallpapersByCat(
        id: Int,
        page:Int,
        perPage:Int
    ): WallpapersByCatResponse
}