package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.WallpapersResponse
import com.anime.live_wallpapershd.model.Slide
import com.anime.live_wallpapershd.repository.SlideRepo
import javax.inject.Inject

class SlideRepoImpl @Inject constructor(
    private val api: ApiService
): SlideRepo {
    override suspend fun getSlide(): List<Slide> {
        val slideResponse = api.getSlide()
        return slideResponse.data
    }
    override suspend fun getSlideWallpapers(slideId: Int, page: Int, perPage: Int):WallpapersResponse {
        return api.getSlideWallpapers(slideId,page, perPage)
    }
}