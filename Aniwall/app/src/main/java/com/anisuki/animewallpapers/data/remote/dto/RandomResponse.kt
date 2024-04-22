package com.anisuki.animewallpapers.data.remote.dto

import com.anisuki.animewallpapers.domain.model.RandomWallpaper

data class RandomResponse(
    val `data`: List<RandomWallpaper>
)