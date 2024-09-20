package com.anime.live_wallpapershd.services

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.anime.live_wallpapershd.R

@SuppressLint("Range", "SetTextI18n", "ShowToast")
fun downloadWallpaper(context: Context, downloadUrl: String, fileName: String) {
    val text = "Wallpaper Download Success"
    val duration = Toast.LENGTH_SHORT
    val dialogView = View.inflate(context, R.layout.progress_dialog_layout, null)
    val progressBar = dialogView.findViewById<ProgressBar>(R.id.progressBar)
    val progressText = dialogView.findViewById<TextView>(R.id.progressText)

    val alertDialog = AlertDialog.Builder(context)
        .setView(dialogView)
        .setCancelable(false)
        .create()

    alertDialog.show()

    val request = DownloadManager.Request(Uri.parse(downloadUrl))
        .setTitle(fileName)
        .setDescription("Downloading")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val downloadId = downloadManager.enqueue(request)

    val query = DownloadManager.Query().setFilterById(downloadId)
    val handler = Handler(Looper.getMainLooper())

    Thread {
        var downloading = true
        while (downloading) {
            val cursor = downloadManager.query(query)
            if (cursor.moveToFirst()) {
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                val downloadedBytes = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                val totalBytes = cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                val progress = ((downloadedBytes * 100L) / totalBytes).toInt()

                when (status) {
                    DownloadManager.STATUS_SUCCESSFUL -> {


                        downloading = false
                        handler.post {
                            alertDialog.dismiss()
                            Toast.makeText(context, text, duration).show()

                        }

                    }
                    DownloadManager.STATUS_FAILED -> {


                        downloading = false
                        handler.post {
                            alertDialog.dismiss()
                        }
                    }
                    else -> {
                        handler.post {
                            progressBar.progress = progress
                            progressText.text = "$progress%"
                        }
                    }
                }
            }
            cursor.close()
            Thread.sleep(100)
        }
    }.start()
}
