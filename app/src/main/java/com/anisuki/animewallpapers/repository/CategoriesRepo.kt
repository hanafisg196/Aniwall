package com.anisuki.animewallpapers.repository

import com.anisuki.animewallpapers.data.dto.CategoriesResponse

interface CategoriesRepo {
    suspend fun getCategories(page:Int, perPage:Int):CategoriesResponse
}