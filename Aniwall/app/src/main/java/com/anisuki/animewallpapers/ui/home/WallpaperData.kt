package com.anisuki.animewallpapers.ui.home

data class WallpaperPage(
    val user: String,
    val description: String,
    val image: String
)

val pages = listOf(
    WallpaperPage(
        user = "Username",
        description = "Description",
        image ="https://mfiles.alphacoders.com/996/thumb-1920-996125.jpg"
    ),
    WallpaperPage(
        user = "Username",
        description = "Description",
        image = "https://mfiles.alphacoders.com/101/thumb-1920-1012679.png"
    ),
    WallpaperPage(
        user = "Username",
        description = "Description",
        image = "https://mfiles.alphacoders.com/101/thumb-1920-1012681.png"
    ),
    WallpaperPage(
        user = "Username",
        description = "Description",
        image = "https://mfiles.alphacoders.com/100/thumb-1920-1002987.jpeg"
    ),
    WallpaperPage(
        user = "Username",
        description = "Description",
        image = "https://mfiles.alphacoders.com/100/thumb-1920-1008387.jpeg"
    )

        )