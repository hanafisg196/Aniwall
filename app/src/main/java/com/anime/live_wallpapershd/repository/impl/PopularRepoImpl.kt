package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.PopularResponse
import com.anime.live_wallpapershd.repository.PopularRepo
import javax.inject.Inject

class PopularRepoImpl @Inject constructor(
    private val api: ApiService
):PopularRepo {

    override suspend fun getPopular(page:Int, perPage:Int):PopularResponse {
        return api.getPopular(page, perPage)

    }
}