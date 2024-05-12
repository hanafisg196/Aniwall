package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.CategoriesResponse
import com.anime.live_wallpapershd.repository.CategoriesRepo
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor(
    private val  api: ApiService
): CategoriesRepo {
    override suspend fun getCategories(page: Int, perPage:Int): CategoriesResponse {
//        delay(3000)
        return api.getCategories(page, perPage)
    }
}