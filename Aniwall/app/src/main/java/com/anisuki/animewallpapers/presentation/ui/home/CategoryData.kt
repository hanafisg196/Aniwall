package com.anisuki.animewallpapers.presentation.ui.home



data class CategoryData(
    val title: String,
    val image: String
)

val catItems = listOf(
    CategoryData(
        title = "Jujutsu Kaisen",
        "https://volt.kyoani-publisher.xyz/wp/jujutsu_kaisen_image.jpeg"
    ),
    CategoryData(
        title = "Anime AMV",
        image =  "https://volt.kyoani-publisher.xyz/wp/amv_image.jpeg"
    ),
    CategoryData(
        title = "Live Wallpaper",
        image = "https://volt.kyoani-publisher.xyz/wp/live_image.png"
    ),
    CategoryData(
        title = "Demon Slayer",
        image = "https://volt.kyoani-publisher.xyz/wp/demon_slayer_image.png"
    ),

)
