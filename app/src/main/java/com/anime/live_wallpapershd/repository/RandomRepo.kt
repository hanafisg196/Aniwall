package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.model.Random
import javax.inject.Inject

class RandomRepo @Inject constructor (
    private val  api: ApiService
) {
    suspend fun getRandoms(): List<Random> {
        val randomResponse = api.getRandom()
        return randomResponse.data

    }
}