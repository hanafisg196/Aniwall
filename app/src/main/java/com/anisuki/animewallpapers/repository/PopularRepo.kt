package com.anisuki.animewallpapers.repository

import com.anisuki.animewallpapers.data.dto.PopularResponse

interface PopularRepo {

    suspend fun getPopular(page:Int, perPage:Int):PopularResponse

}