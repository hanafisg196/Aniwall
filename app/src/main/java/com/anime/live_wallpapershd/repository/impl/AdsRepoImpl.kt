package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.AdsResponse
import com.anime.live_wallpapershd.repository.AdsRepo
import javax.inject.Inject

class AdsRepoImpl @Inject constructor(
    private val  api: ApiService
):AdsRepo {
    override suspend fun getAds(): AdsResponse {
        return api.getAds()
    }

}