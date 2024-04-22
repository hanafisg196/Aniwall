package com.anisuki.animewallpapers.presentation.ui.home

import com.anisuki.animewallpapers.domain.model.RandomWallpaper

data class RandomListState(
    val isLoading: Boolean = false,
    val random: List<RandomWallpaper> = emptyList(),
    val error: String=""

)