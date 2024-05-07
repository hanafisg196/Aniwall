package com.anisuki.animewallpapers.presentation.detail.components

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anisuki.animewallpapers.R
import com.anisuki.animewallpapers.common.Constants.ITEM_URL
import com.anisuki.animewallpapers.model.Wallpaper
import com.anisuki.animewallpapers.services.VideoWallpaperService
import com.anisuki.animewallpapers.services.downloadDataLiveWallpaper
import com.anisuki.animewallpapers.ui.fonts.Fonts
import com.pixplicity.easyprefs.library.Prefs
import java.io.File
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(wallpaper: Wallpaper)
{
    val context = LocalContext.current
    val dataUrl = ITEM_URL + wallpaper.type


        var showDialog by remember {
            mutableStateOf(false)
        }
    if (showDialog)
    {
        DialogSheet (onDismiss = {
            showDialog = false
        }, wallpaper = wallpaper)
    }


    BottomSheetScaffold(
        sheetPeekHeight = 120.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, start = 15.dp, end = 15.dp)

            ) {
                Text(
                    text = "#Tag" ,
                    color = Color.Gray,
                    fontFamily = Fonts.fontFamily,
                    fontSize = 14.sp
                )
                Text(
                    text = wallpaper.title,
                    color = Color.Black,
                    fontFamily = Fonts.fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 16.dp),
                    contentAlignment = Alignment.Center

                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(colorResource(id = R.color.blueBird))
                                .clickable {
                                    if (dataUrl.contains(".mp4")) {
                                        val fileName = "live_wallpaper.mp4"
                                        downloadDataLiveWallpaper(context, dataUrl, fileName) { filePath ->
                                            Prefs.putString("video_file", filePath)
                                            VideoWallpaperService.start(context,filePath)
                                        }
                                    } else {
                                        showDialog = true
                                    }
                                }
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.aplly),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                                tint = Color.White
                            )
                        }
                        Text(
                            text = "Apply",
                            color = Color.Gray,
                            fontFamily = Fonts.fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center

                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(start = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(colorResource(id = R.color.pinkCustom))
                                .clickable {
                                
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.download),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                                tint = Color.White
                            )
                        }
                        Text(
                            text = "Download",
                            color = Color.Gray,
                            fontFamily = Fonts.fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp
                        )
                    }
                }
            }


             Row(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(64.dp),
                 ){

            }
        } ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        {
                //
        }

    }

}



