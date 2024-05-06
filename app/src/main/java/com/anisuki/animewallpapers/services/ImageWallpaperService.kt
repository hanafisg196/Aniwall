package com.anisuki.animewallpapers.services

import android.app.ProgressDialog
import android.app.WallpaperManager
import android.content.Context
import android.graphics.BitmapFactory
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

fun bothWallpaperSet(
    context: Context,
    scope: CoroutineScope,
    contentUrl: String
) {
    val progressDialog = ProgressDialog(context)
    progressDialog.setMessage("Setting wallpaper...")
    progressDialog.show()

    val wallpaperManager = WallpaperManager.getInstance(context)
    scope.launch {
        try {
            val bitmap = withContext(Dispatchers.IO) {
                BitmapFactory.decodeStream(URL(contentUrl).openConnection().getInputStream())
            }
            wallpaperManager.setBitmap(bitmap)
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Apply Success", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle errors
        } finally {
            progressDialog.dismiss()
        }
    }
}

fun homeWallpaperSet(
    context: Context,
    scope: CoroutineScope,
    contentUrl: String
) {
    val progressDialog = ProgressDialog(context)
    progressDialog.setMessage("Setting wallpaper...")
    progressDialog.show()

    val wallpaperManager = WallpaperManager.getInstance(context)
    scope.launch {
        try {
            val bitmap = withContext(Dispatchers.IO) {
                BitmapFactory.decodeStream(URL(contentUrl).openConnection().getInputStream())
            }
            wallpaperManager.setBitmap(
                bitmap,
                null,
                false,
                WallpaperManager.FLAG_SYSTEM

            )
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Apply Success", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle errors
        } finally {
            progressDialog.dismiss()
        }
    }
}

fun lockWallpaperSet(
    context: Context,
    scope: CoroutineScope,
    contentUrl: String
) {
    val progressDialog = ProgressDialog(context)
    progressDialog.setMessage("Setting wallpaper...")
    progressDialog.show()

    val wallpaperManager = WallpaperManager.getInstance(context)
    scope.launch {
        try {
            val bitmap = withContext(Dispatchers.IO) {
                BitmapFactory.decodeStream(URL(contentUrl).openConnection().getInputStream())
            }
            wallpaperManager.setBitmap(
                bitmap,
                null,
                false,
                WallpaperManager.FLAG_LOCK

            )
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Apply Success", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle errors
        } finally {
            progressDialog.dismiss()
        }
    }
}
