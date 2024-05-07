package com.anisuki.animewallpapers.repository.impl

import com.anisuki.animewallpapers.data.ApiService
import com.anisuki.animewallpapers.data.dto.PopularResponse
import com.anisuki.animewallpapers.repository.PopularRepo
import javax.inject.Inject

class PopularRepoImpl @Inject constructor(
    private val api: ApiService
):PopularRepo {

    override suspend fun getPopular(page:Int, perPage:Int):PopularResponse {
        return api.getPopular(page, perPage)

    }
}