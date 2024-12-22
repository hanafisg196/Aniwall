package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.WallpapersResponse
import com.anime.live_wallpapershd.model.Slide

interface SlideRepo {
    suspend fun getSlide(): List<Slide>
    suspend fun getSlideWallpapers(slideId: Int,page:Int, perPage:Int):WallpapersResponse
}