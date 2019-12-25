package `in`.co.ankitarora.mvvmsamplegithubrepos.util

import android.content.Context
import java.io.File


class FileCache(context: Context) {
    private var cacheDir: File? = null
    fun getFile(url: String): File {
        val filename = url.hashCode().toString()
        return File(cacheDir, filename)
    }

    fun clear() {
        val files: Array<File> = cacheDir?.listFiles() ?: return
        for (f in files) f.delete()
    }

    init {
        cacheDir = context.cacheDir
        if (cacheDir?.exists() != true) cacheDir?.mkdirs()
    }
}