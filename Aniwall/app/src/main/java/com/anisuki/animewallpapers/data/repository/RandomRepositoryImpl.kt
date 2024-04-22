package com.anisuki.animewallpapers.data.repository

import com.anisuki.animewallpapers.data.remote.Api
import com.anisuki.animewallpapers.data.remote.dto.RandomResponse
import com.anisuki.animewallpapers.domain.repository.RandomRepository
import javax.inject.Inject

class RandomRepositoryImpl @Inject constructor(
    private val api : Api
) : RandomRepository{
    override suspend fun getRandom(): RandomResponse {
        return api.getRandom()
    }
}