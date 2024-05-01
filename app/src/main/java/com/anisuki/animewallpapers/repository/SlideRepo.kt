package com.anisuki.animewallpapers.repository

import com.anisuki.animewallpapers.data.ApiService
import com.anisuki.animewallpapers.model.Random
import com.anisuki.animewallpapers.model.Slide
import javax.inject.Inject

class SlideRepo @Inject constructor(
    private val api : ApiService
) {
    suspend fun getSlide(): List<Slide> {
        val slideResponse = api.getSlide()
        return slideResponse.data
    }
}