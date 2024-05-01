package com.anisuki.animewallpapers.model

import com.google.gson.annotations.SerializedName


data class Random(
    val id: Int,
    val thumbnail: String,
    val title: String,
    val type: String,
    val view: Int
)