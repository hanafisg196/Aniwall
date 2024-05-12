package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.model.Slide
import javax.inject.Inject

class SlideRepo @Inject constructor(
    private val api : ApiService
) {
    suspend fun getSlide(): List<Slide> {
        val slideResponse = api.getSlide()
        return slideResponse.data
    }
}