package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.CategoriesResponse

interface CategoriesRepo {
    suspend fun getCategories(page:Int, perPage:Int):CategoriesResponse
}