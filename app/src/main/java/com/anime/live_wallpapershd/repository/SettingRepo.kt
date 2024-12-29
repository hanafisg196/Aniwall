package com.anime.live_wallpapershd.repository

import com.anime.live_wallpapershd.data.dto.SettingResponse

interface SettingRepo {
    suspend fun setting():SettingResponse
}