package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.CategoryResponse
import com.anime.live_wallpapershd.data.dto.WallpapersByCatResponse
import com.anime.live_wallpapershd.model.Category
import com.anime.live_wallpapershd.repository.WallpapersByCatRepo
import javax.inject.Inject

class WallpapersByCatRepoImpl @Inject constructor(
    private val  api: ApiService
):WallpapersByCatRepo{
    override suspend fun getWallpapersByCat(id:Int, page:Int, perPage:Int): WallpapersByCatResponse
    {
        return api.getWallpapersByCat(id, page, perPage)
    }
    override suspend fun getWallpapersCatName(id: Int): CategoryResponse {
       return api.getWallpapersCatName(id)
    }


}