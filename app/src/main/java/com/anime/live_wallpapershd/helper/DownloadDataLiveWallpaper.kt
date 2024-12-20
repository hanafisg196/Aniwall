package com.anime.live_wallpapershd.helper

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.anime.live_wallpapershd.R
import java.io.File

fun downloadDataLiveWallpaper(context: Context, downloadUrl: String, fileName: String, callback: (String) -> Unit) {
    val dirPath = context.getExternalFilesDir("VIDEO")?.absolutePath ?: ""
    val file = File(dirPath, fileName)
    if (file.exists()) {
        file.delete()
    }

    // Create a custom layout for the progress dialog
    val dialogView = View.inflate(context, R.layout.progress_dialog_layout, null)
    val progressBar = dialogView.findViewById<ProgressBar>(R.id.progressBar)
    val progressText = dialogView.findViewById<TextView>(R.id.progressText)

    // Build the AlertDialog
    val alertDialog = AlertDialog.Builder(context)
        .setView(dialogView)
        .setCancelable(false)
        .create()

    alertDialog.show()

    val request = DownloadManager.Request(Uri.parse(downloadUrl))
        .setTitle(fileName)
        .setDescription("Downloading")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalFilesDir(context, "VIDEO", fileName)

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val downloadId = downloadManager.enqueue(request)

    val query = DownloadManager.Query().setFilterById(downloadId)

    // Create a Handler for the main thread
    val handler = Handler(Looper.getMainLooper())
    handler.postDelayed(object : Runnable {
        @SuppressLint("Range", "SetTextI18n")
        override fun run() {
            val cursor = downloadManager.query(query)
            if (cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                if (status == DownloadManager.STATUS_SUCCESSFUL || status == DownloadManager.STATUS_FAILED) {
                    alertDialog.dismiss()
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        val downloadedFile = File(dirPath, fileName).absolutePath
                        callback.invoke(downloadedFile)
                    }
                    return
                }
                progressBar.isIndeterminate = false
                val bytesDownloaded = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                val bytesTotal = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                val progress = (bytesDownloaded * 100f / bytesTotal).toInt()
                progressBar.progress = progress
                progressText.text = "$progress%"
            }
            cursor.close()
            handler.postDelayed(this, 1000)
        }
    }, 1000)
}
