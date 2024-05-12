package com.anime.live_wallpapershd.ui.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.anime.live_wallpapershd.R

object Fonts {
    val fontFamily = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_regular, FontWeight.Normal),
    )
}