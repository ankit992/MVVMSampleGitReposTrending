package `in`.co.ankitarora.mvvmsamplegithubrepos.di

import `in`.co.ankitarora.mvvmsamplegithubrepos.App
import `in`.co.ankitarora.mvvmsamplegithubrepos.BuildConfig
import `in`.co.ankitarora.mvvmsamplegithubrepos.IdlingResources
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfoApi
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import io.reactivex.Single
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton


@Module
open class ApiModule {

    @Singleton
    @Provides
    open fun provideGitRepoInfoListApiService(app: App): Single<List<GitRepoInfo>> {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getOkHttpClient(app))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GitRepoInfoApi::class.java).getListOfTrendingGitRepos()
    }


    private fun getOkHttpClient(app: Application): OkHttpClient {

        val cacheInterceptor = Interceptor { chain ->
            val originalResponse = chain.proceed(chain.request())
            if (isNetworkAvailable(app)) {
                val maxAge = 60 // read from cache for 1 minute
                return@Interceptor originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .build()
            } else {
                val maxStale = 60 * 60 * 24 * 10 // tolerate 10-days stale
                return@Interceptor originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
            }
        }
        val httpCacheDirectory = File(app.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024 // 10 MiB

        val cache = Cache(httpCacheDirectory, cacheSize.toLong())
        val okHttpClient =
            OkHttpClient.Builder().addNetworkInterceptor(cacheInterceptor).cache(cache).build()
        IdlingResources.registerOkHttp(okHttpClient)
        return okHttpClient
    }

    private fun isNetworkAvailable(app: Application): Boolean {
        val cm = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }


}



