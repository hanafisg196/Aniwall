package com.anisuki.animewallpapers.repository.impl

import com.anisuki.animewallpapers.data.ApiService
import com.anisuki.animewallpapers.data.dto.WallpapersResponse
import com.anisuki.animewallpapers.repository.WallpapersRepo
import javax.inject.Inject

class WallpapersRepoImpl @Inject constructor(
    private val api: ApiService
):WallpapersRepo {

    override suspend fun getWallpapers(page: Int, perPage: Int): WallpapersResponse {
//        delay(3000)
        return api.getLatest(page, perPage)

    }

}