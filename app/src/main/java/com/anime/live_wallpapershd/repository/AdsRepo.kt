package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.AdsResponse

interface AdsRepo {
    suspend  fun getAds(): AdsResponse
}