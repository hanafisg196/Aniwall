package com.anisuki.animewallpapers.repository

import com.anisuki.animewallpapers.data.dto.WallpapersResponse

interface WallpapersRepo {
    suspend fun getWallpapers(page: Int, perPage: Int): WallpapersResponse
}