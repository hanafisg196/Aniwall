package com.apptesting.test.activity

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsets
import android.webkit.MimeTypeMap
import android.webkit.URLUtil
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.apptesting.test.R
import com.apptesting.test.model.AllData
import com.apptesting.test.utils.Const
import com.apptesting.test.utils.MyApplication
import com.apptesting.test.utils.SessionManager
import eightbitlab.com.blurview.BlurAlgorithm
import eightbitlab.com.blurview.BlurView
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.concurrent.Executors

open class BaseActivity : AppCompatActivity() {


    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = window.decorView
        decorView.setOnApplyWindowInsetsListener { v: View, insets: WindowInsets? ->
            val defaultInsets = v.onApplyWindowInsets(insets)
            defaultInsets.replaceSystemWindowInsets(
                defaultInsets.systemWindowInsetLeft,
                0,
                defaultInsets.systemWindowInsetRight,
                defaultInsets.systemWindowInsetBottom
            )
        }
        ViewCompat.requestApplyInsets(decorView)
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.transparent)
        sessionManager = SessionManager(this)

        val myApplication: MyApplication = getApplication() as MyApplication

        if (myApplication.intAd == null) {
            myApplication.initializeIntAd(this)

        }
        if (myApplication.rewardAd == null) {
            myApplication.initializeRewardAd(this)
        }


    }

    interface OnDownload {
        fun onComplete()
        fun onError()
    }

    lateinit var onDownloadCallback: OnDownload

    public fun downloadWall(wallpapersItem: AllData.WallpapersItem, callback: OnDownload) {


        onDownloadCallback = callback
        val myExecutor = Executors.newSingleThreadExecutor()

        if (wallpapersItem.wallpaperType == 0) {

            val myHandler = Handler(Looper.getMainLooper())
            var mImage: Bitmap?

            myExecutor.execute {
                mImage = mLoad(Const.ITEM_URL + wallpapersItem.content!!)
                myHandler.post {
                    if (mImage != null) {
                        mSaveMediaToStorage(mImage)
                    }
                }
            }
        } else {


            downloadFile(
                Const.ITEM_URL + wallpapersItem.content!!
            )


        }


    }

    private fun mSaveMediaToStorage(bitmap: Bitmap?) {
        val filename = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val imageFile = File(imagesDir, filename)
            fos = FileOutputStream(imageFile)
        }
        fos?.use {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this, getString(R.string.saved_to_gallery), Toast.LENGTH_SHORT).show()
            onDownloadCallback.onComplete()
        }
    }

    fun getPath(): String? {


        var dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

//        val state = Environment.getExternalStorageState()
//        var filesDir: File? = if (Environment.MEDIA_MOUNTED == state) {
//            // We can read and write the media
//            getExternalFilesDir(null)
//        } else {
//            // Load another directory, probably local memory
//            this.filesDir
//        }
//        return filesDir!!.path
        return dir!!.absolutePath
    }

    private fun mStringToURL(string: String): URL? {
        try {
            return URL(string)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        return null
    }


    private fun downloadFile(fileURL: String) {
        val fileName =
            URLUtil.guessFileName(fileURL, null, MimeTypeMap.getFileExtensionFromUrl(fileURL))
        val path: String? = getPath()



        if (path != null) {
            val file = File("$path/$fileName")
            var downloadID = PRDownloader.download(fileURL, path, fileName).build()
                .setOnStartOrResumeListener { }
                .setOnCancelListener { }
                .setOnProgressListener { }
                .start(object : OnDownloadListener {
                    override fun onDownloadComplete() {
                        onDownloadCallback.onComplete()
                        Toast.makeText(
                            this@BaseActivity,
                            getString(R.string.saved_to_gallery),
                            Toast.LENGTH_SHORT
                        ).show()
                        MediaScannerConnection.scanFile(
                            this@BaseActivity, arrayOf<String>("$path/$fileName"), null
                        ) { _, _ ->
                        }

                    }

                    override fun onError(error: Error?) {

                        Log.i("TAG", "onError: " + error?.serverErrorMessage)
                        onDownloadCallback.onError()
                        Toast.makeText(
                            this@BaseActivity,
                            getString(R.string.something_went_wrong),
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                })

        }
    }


    private fun mLoad(string: String): Bitmap? {
        val url: URL = mStringToURL(string)!!
        val connection: HttpURLConnection?
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream: InputStream = connection.inputStream
            val bufferedInputStream = BufferedInputStream(inputStream)
            return BitmapFactory.decodeStream(bufferedInputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
        }
        return null
    }


    open fun removeBlur(blurView: BlurView) {
        blurView.setBlurRadius(0f)

    }

    open fun setBlur(blurView: BlurView, rootView: ViewGroup) {
        val windowBackground = window.decorView.background
        val algorithm: BlurAlgorithm = getBlurAlgorithm()
        blurView.setupWith(rootView, algorithm)
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(20f)

    }

    open fun getBlurAlgorithm(): BlurAlgorithm {
        val algorithm: BlurAlgorithm
        algorithm = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            RenderEffectBlur()
        } else {
            RenderScriptBlur(this)
        }
        return algorithm
    }
}