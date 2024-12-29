package com.anime.live_wallpapershd.repository.impl

import com.anime.live_wallpapershd.data.ApiService
import com.anime.live_wallpapershd.data.dto.SettingResponse
import com.anime.live_wallpapershd.repository.SettingRepo
import javax.inject.Inject

class SettingRepoImpl @Inject constructor(
    private val api: ApiService
): SettingRepo {
    override suspend fun setting(): SettingResponse {
        return api.setting()
    }

}