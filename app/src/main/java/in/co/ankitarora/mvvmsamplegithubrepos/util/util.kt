package `in`.co.ankitarora.mvvmsamplegithubrepos.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val imgLoader = ImageLoader(this.context)
    imgLoader.DisplayImage(uri, this, progressDrawable)
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?) {

    view.loadImage(url, getProgressDrawable(view.context))
}