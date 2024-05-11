package com.anisuki.animewallpapers.repository.impl

import com.anisuki.animewallpapers.data.ApiService
import com.anisuki.animewallpapers.data.dto.WallpapersByCatResponse
import com.anisuki.animewallpapers.repository.WallpapersByCatRepo
import javax.inject.Inject

class WallpapersByCatRepoImpl @Inject constructor(
    private val  api: ApiService
):WallpapersByCatRepo{
    override suspend fun getWallpapersByCat(id:Int, page:Int, perPage:Int): WallpapersByCatResponse
    {
        return api.getWallpapersByCat(id, page, perPage)
    }

}