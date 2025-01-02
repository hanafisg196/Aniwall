package com.anime.live_wallpapershd.presentation.wallpapers.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anime.live_wallpapershd.R
@Composable
fun SearchBar(
    searchQuery: String,
    onQueryChange: (String) -> Unit
) {
    var query by remember { mutableStateOf(searchQuery) }

    OutlinedTextField(
        value = query,
        onValueChange = { newQuery ->
            query = newQuery
            onQueryChange(newQuery)
        },
        placeholder = {
            Text(
                text = "Search",
                style = androidx.compose.ui.text.TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
                tint = Color.Gray
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = {
                    query = ""
                    onQueryChange("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "clear",
                        tint = Color.Gray
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 10.dp)
            .height(50.dp)
            .shadow(4.dp, shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            cursorColor = colorResource(id = R.color.blueBird),
            focusedBorderColor = colorResource(id = R.color.blueBird),
            unfocusedBorderColor = Color.Gray
        ),
        textStyle = androidx.compose.ui.text.TextStyle(
            color = colorResource(id = R.color.black),
            fontSize = 16.sp
        )
    )
}
