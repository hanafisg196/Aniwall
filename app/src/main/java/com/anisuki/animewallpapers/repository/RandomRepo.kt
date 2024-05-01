package com.anisuki.animewallpapers.repository

import com.anisuki.animewallpapers.data.ApiService
import com.anisuki.animewallpapers.model.Random
import javax.inject.Inject

class RandomRepo @Inject constructor (
    private val  api: ApiService
) {
    suspend fun getRandoms(): List<Random> {
        val randomResponse = api.getRandom()
        return randomResponse.data

    }
}