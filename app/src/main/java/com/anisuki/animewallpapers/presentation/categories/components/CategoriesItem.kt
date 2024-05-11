package com.anisuki.animewallpapers.presentation.categories.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.anisuki.animewallpapers.common.Constants
import com.anisuki.animewallpapers.model.Categories
import com.anisuki.animewallpapers.ui.fonts.Fonts

@Composable
fun CategoriesItem(
    categories: Categories,
    onItemClick: (Categories) -> Unit
) {

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),

    ) {
        Card (
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .width(350.dp)
                .height(150.dp)
                .padding(vertical = 8.dp)
                .clickable {
                    onItemClick(categories)
                }
        ){
            val context = LocalContext.current
            val imageUrl = categories.image
            Box(

                modifier = Modifier
                    .fillMaxSize()

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(Constants.ITEM_URL +imageUrl)
                        .crossfade(true)
                        .scale(Scale.FILL)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                ) {

                    Text(
                        text = categories.name,
                        color = Color.White,
                        fontFamily = Fonts.fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 25.sp
                    )

                }

            }
        }
    }

}