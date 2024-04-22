package com.anisuki.animewallpapers.domain.repository

import com.anisuki.animewallpapers.data.remote.dto.RandomResponse

interface RandomRepository {

    suspend fun getRandom():RandomResponse

}