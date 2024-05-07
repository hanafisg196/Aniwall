package com.anisuki.animewallpapers.services

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Handler
import java.io.File

fun downloadDataLiveWallpaper(context: Context, downloadUrl: String, fileName: String, callback: (String) -> Unit) {

    val dirPath = context.getExternalFilesDir("VIDEO")?.absolutePath ?: ""
    val file = File(dirPath, fileName)
    if (file.exists()) {
        file.delete()
    }
    val progressDialog = ProgressDialog(context)
    progressDialog.setMessage("Downloading...")
    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
    progressDialog.isIndeterminate = true
    progressDialog.setCancelable(false)
    progressDialog.show()

    val request = DownloadManager.Request(Uri.parse(downloadUrl))
        .setTitle(fileName)
        .setDescription("Downloading")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalFilesDir(context, "VIDEO", fileName)

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    val downloadId = downloadManager.enqueue(request)

    val query = DownloadManager.Query().setFilterById(downloadId)

    val handler = Handler()
    handler.postDelayed(object : Runnable {
        @SuppressLint("Range")
        override fun run() {
            val cursor = downloadManager.query(query)
            if (cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                if (status == DownloadManager.STATUS_SUCCESSFUL || status == DownloadManager.STATUS_FAILED) {
                    progressDialog.dismiss()
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        val dirPath = context.getExternalFilesDir("VIDEO")?.absolutePath ?: ""
                        val downloadedFile = File(dirPath, fileName).absolutePath
                        callback.invoke(downloadedFile)
                    }
                    return
                }
                progressDialog.isIndeterminate = false
                val bytesDownloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                val bytesTotal = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                val progress = (bytesDownloaded * 100f / bytesTotal).toInt()
                progressDialog.progress = progress
            }
            cursor.close()
            handler.postDelayed(this, 1000)
        }
    }, 1000)
}