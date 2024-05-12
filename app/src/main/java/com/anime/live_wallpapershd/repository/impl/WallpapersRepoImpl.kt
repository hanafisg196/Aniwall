package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.WallpapersResponse
import com.anime.live_wallpapershd.repository.WallpapersRepo
import javax.inject.Inject

class WallpapersRepoImpl @Inject constructor(
    private val api: ApiService
):WallpapersRepo {

    override suspend fun getWallpapers(page: Int, perPage: Int): WallpapersResponse {
//        delay(3000)
        return api.getLatest(page, perPage)

    }

}