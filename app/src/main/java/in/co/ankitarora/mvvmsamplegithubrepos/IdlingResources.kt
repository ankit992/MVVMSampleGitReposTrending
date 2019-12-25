package `in`.co.ankitarora.mvvmsamplegithubrepos

import androidx.test.espresso.IdlingRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient

object IdlingResources {
    fun registerOkHttp(client: OkHttpClient) {
        if (BuildConfig.BUILD_TYPE == "qa")
            IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", client));
    }
}