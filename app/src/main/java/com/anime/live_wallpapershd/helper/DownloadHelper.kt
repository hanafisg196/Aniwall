package com.anime.live_wallpapershd.helper

import android.app.ProgressDialog
import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import java.io.File


fun download(context: Context, url: String, fileName: String) {
    val progressDialog = ProgressDialog(context).apply {
        setTitle("Downloading")
        setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        isIndeterminate = false
        setCancelable(false)
        max = 100
        show()
    }
    val dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath
    val file = File(dirPath)
    if (!file.exists()) {
        file.mkdirs()
    }
         PRDownloader.download(url, dirPath, fileName)
        .build()
        .setOnStartOrResumeListener {
            Log.d("DownloadService", "Download Started")
        }
        .setOnPauseListener {
            Toast.makeText(context, "Download Paused", Toast.LENGTH_SHORT).show()
        }
        .setOnCancelListener {
            progressDialog.dismiss()
            Toast.makeText(context, "Download Cancelled", Toast.LENGTH_SHORT).show()
        }
        .setOnProgressListener {
           val progressPercent = (it.currentBytes * 100 / it.totalBytes).toInt()
            progressDialog.progress = progressPercent
            Log.d("DownloadService", "Progress: $progressPercent%")
        }
        .start(object : OnDownloadListener {
            override fun onDownloadComplete() {
                progressDialog.dismiss()
                Toast.makeText(context, "Download Success", Toast.LENGTH_SHORT).show()
                Log.e("DownloadService", "Complete Download")
            }

            override fun onError(error: com.downloader.Error?) {
                error?.let {
                    Log.e("DownloadService", "Error: ${it.serverErrorMessage ?: "Unknown Error"}")
                    Toast.makeText(context, "Download Failed: ${it.serverErrorMessage}", Toast.LENGTH_LONG).show()
                }
            }
        })
}

fun downloadVideoSet(context: Context, url: String, dirPath: String, fileName: String, onDownloadComplete: (String) -> Unit) {
    val progressDialog = ProgressDialog(context).apply {
        setTitle("Downloading")
        setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        isIndeterminate = false
        setCancelable(false)
        max = 100
        show()
    }
       PRDownloader.download(url, dirPath, fileName)
        .build()
        .setOnStartOrResumeListener {
            Log.d("DownloadService", "Download Started")
        }
        .setOnPauseListener {
            Toast.makeText(context, "Download Paused", Toast.LENGTH_SHORT).show()
        }
        .setOnCancelListener {
            progressDialog.dismiss()
            Toast.makeText(context, "Download Cancelled", Toast.LENGTH_SHORT).show()
        }
        .setOnProgressListener {
            val progressPercent = (it.currentBytes * 100 / it.totalBytes).toInt()
            progressDialog.progress = progressPercent
            Log.d("DownloadService", "Progress: $progressPercent%")

        }
        .start(object : OnDownloadListener {
            override fun onDownloadComplete() {
                progressDialog.dismiss()
                val filePath = "$dirPath/$fileName"
                onDownloadComplete(filePath)
                Toast.makeText(context, "Download Success", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: com.downloader.Error?) {
                error?.let {
                    Log.e("DownloadService", "Error: ${it.serverErrorMessage ?: "Unknown Error"}")
                    Toast.makeText(context, "Download Failed: ${it.serverErrorMessage}", Toast.LENGTH_LONG).show()
                }
            }
        })
}




