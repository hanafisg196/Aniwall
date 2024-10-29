package com.anime.live_wallpapershd.services

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.FileNotFoundException

fun createMultipartBodyImage(context: Context, uri: Uri, paramName: String): MultipartBody.Part? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val requestBody = inputStream.readBytes().toRequestBody("image/*".toMediaTypeOrNull())
        MultipartBody.Part.createFormData(paramName, "filename.jpg", requestBody)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        null
    }
}

fun createMultipartBodyVideo(context: Context, uri: Uri, paramName: String): MultipartBody.Part? {
    return try {
        val mimeType = context.contentResolver.getType(uri)
        if (mimeType != "video/mp4") return null
        val inputStream = context.contentResolver.openInputStream(uri) ?: return null
        val requestBody = inputStream.readBytes().toRequestBody("video/mp4".toMediaTypeOrNull())
        MultipartBody.Part.createFormData(paramName, "video_file.mp4", requestBody)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
        null
    }
}



