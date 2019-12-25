package `in`.co.ankitarora.mvvmsamplegithubrepos.util

import `in`.co.ankitarora.mvvmsamplegithubrepos.R
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class ImageLoader(context: Context) {
    var memoryCache: MemoryCache = MemoryCache()
    var fileCache: FileCache = FileCache(context)
    private val imageViews: MutableMap<ImageView, String> =
        Collections.synchronizedMap(WeakHashMap<ImageView, String>())
    private var executorService: ExecutorService = Executors.newFixedThreadPool(5)
    val stubId: Int = R.drawable.ic_launcher_background

    fun DisplayImage(
        url: String?,
        imageView: ImageView,
        progressDrawable: CircularProgressDrawable
    ) {
        if (url.isNullOrBlank()) {
            imageView.setImageResource(R.drawable.ic_launcher_background)
            return
        }
        imageViews[imageView] = url
        val bitmap: Bitmap? = memoryCache[url]
        if (bitmap != null) imageView.setImageBitmap(bitmap) else {
            queuePhoto(url, imageView)
            imageView.setImageDrawable(progressDrawable)
        }
    }

    private fun queuePhoto(url: String, imageView: ImageView) {
        val p = PhotoToLoad(url, imageView)
        executorService.submit(PhotosLoader(p))
    }

    private fun getBitmap(url: String): Bitmap? {
        val f: File = fileCache.getFile(url)
        //from SD cache
        val b = decodeFile(f)
        return b
            ?: try {
                val imageUrl = URL(url)
                val conn: HttpURLConnection = imageUrl.openConnection() as HttpURLConnection
                conn.connectTimeout = 30000
                conn.readTimeout = 30000
                conn.instanceFollowRedirects = true
                val inputStream: InputStream = conn.inputStream
                val os: OutputStream = FileOutputStream(f)
                copyStream(inputStream, os)
                os.close()
                val bitmap: Bitmap? = decodeFile(f)
                bitmap
            } catch (ex: Throwable) {
                ex.printStackTrace()
                if (ex is OutOfMemoryError) memoryCache.clear()
                null
            }
        //from web
    }

    private fun copyStream(inputStream: InputStream, os: OutputStream) {

        val bufferSize = 1024
        try {
            val bytes = ByteArray(bufferSize)
            while (true) {
                val count: Int = inputStream.read(bytes, 0, bufferSize)
                if (count == -1) break
                os.write(bytes, 0, count)
            }
        } catch (ex: Exception) {
        }
    }

    private fun decodeFile(f: File): Bitmap? {
        try {
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            BitmapFactory.decodeStream(FileInputStream(f), null, o)
            val size = 1080
            var widthTmp = o.outWidth
            var heightTmp = o.outHeight
            var scale = 1
            while (true) {
                if (widthTmp / 2 < size || heightTmp / 2 < size) break
                widthTmp /= 2
                heightTmp /= 2
                scale *= 2
            }
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            return BitmapFactory.decodeStream(FileInputStream(f), null, o2)
        } catch (e: FileNotFoundException) {
        }
        return null
    }

    //Task for the queue
    inner class PhotoToLoad(var url: String, i: ImageView) {
        var imageView: ImageView = i
    }

    internal inner class PhotosLoader(var photoToLoad: PhotoToLoad) : Runnable {
        override fun run() {
            if (imageViewReused(photoToLoad)) return
            val bmp = getBitmap(photoToLoad.url)
            memoryCache.put(photoToLoad.url, bmp)
            if (imageViewReused(photoToLoad)) return
            val bd = BitmapDisplayer(bmp, photoToLoad)
            val a = photoToLoad.imageView.getContext() as Activity
            a.runOnUiThread(bd)
        }

    }

    fun imageViewReused(photoToLoad: PhotoToLoad): Boolean {
        val tag = imageViews[photoToLoad.imageView]
        return tag == null || tag != photoToLoad.url
    }

    //Used to display bitmap in the UI thread
    internal inner class BitmapDisplayer(var bitmap: Bitmap?, var photoToLoad: PhotoToLoad) :
        Runnable {
        override fun run() {
            if (imageViewReused(photoToLoad)) return
            if (bitmap != null) photoToLoad.imageView.setImageBitmap(bitmap) else photoToLoad.imageView.setImageResource(
                stubId
            )
        }

    }

    fun clearCache() {
        memoryCache.clear()
        fileCache.clear()
    }

}