package com.anisuki.animewallpapers.repository.impl

import com.anisuki.animewallpapers.data.ApiService
import com.anisuki.animewallpapers.data.dto.CategoriesResponse
import com.anisuki.animewallpapers.repository.CategoriesRepo
import javax.inject.Inject

class CategoriesRepoImpl @Inject constructor(
    private val  api: ApiService
): CategoriesRepo {
    override suspend fun getCategories(page: Int, perPage:Int): CategoriesResponse {
//        delay(3000)
        return api.getCategories(page, perPage)
    }
}