package com.anisuki.animewallpapers.repository

import com.anisuki.animewallpapers.data.dto.WallpapersByCatResponse

interface WallpapersByCatRepo {
    suspend fun getWallpapersByCat(
        id: Int,
        page:Int,
        perPage:Int
    ): WallpapersByCatResponse
}