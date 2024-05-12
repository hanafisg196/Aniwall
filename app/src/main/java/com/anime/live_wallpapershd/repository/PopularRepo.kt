package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.PopularResponse

interface PopularRepo {

    suspend fun getPopular(page:Int, perPage:Int):PopularResponse

}