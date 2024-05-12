import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.Environment


@SuppressLint("Range")
fun downloadWallpaper(context: Context, downloadUrl: String, fileName: String) {

    val progressDialog = ProgressDialog(context)
    progressDialog.setMessage("Downloading File...")
    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
    progressDialog.isIndeterminate = false
    progressDialog.setCancelable(false)
    progressDialog.max = 100
    progressDialog.show()


    val request = DownloadManager.Request(Uri.parse(downloadUrl))
        .setTitle(fileName)
        .setDescription("Downloading")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)


    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    val downloadId = downloadManager.enqueue(request)


    val query = DownloadManager.Query().setFilterById(downloadId)
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
                        progressDialog.dismiss()

                    }
                    DownloadManager.STATUS_FAILED -> {
                        downloading = false
                        progressDialog.dismiss()
                        // Handle failure
                    }
                    else -> progressDialog.progress = progress
                }
            }
            cursor.close()
            Thread.sleep(100)
        }
    }.start()
}
