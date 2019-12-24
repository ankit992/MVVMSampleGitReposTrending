package `in`.co.ankitarora.mvvmsamplegithubrepos.di

import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfoApi
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfoApiService
import android.app.Application
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
open class ApiModule {
    private val BASE_URL = "https://github-trending-api.now.sh/"

    @Provides
    fun provideGitRepoInfoListApi(): GitRepoInfoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .client(getOkHttpClient(app))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GitRepoInfoApi::class.java)
    }




    @Singleton
    @Provides
    open fun provideGitRepoInfoListApiService(): GitRepoInfoApiService {
        return GitRepoInfoApiService()
    }


//    private fun getOkHttpClient(): OkHttpClient {
//
//        val cacheInterceptor = Interceptor { chain ->
//            val originalResponse = chain.proceed(chain.request())
//            if (isNetworkAvailable(app)) {
//                val maxAge = 60 // read from cache for 1 minute
//                return@Interceptor originalResponse.newBuilder()
//                    .header("Cache-Control", "public, max-age=$maxAge")
//                    .build()
//            } else {
//                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
//                return@Interceptor originalResponse.newBuilder()
//                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
//                    .build()
//            }
//        }
//        val httpCacheDirectory = File(app.cacheDir, "responses")
//        val cacheSize = 10 * 1024 * 1024 // 10 MiB
//
//        val cache = Cache(httpCacheDirectory, cacheSize.toLong())
//        return OkHttpClient.Builder().addNetworkInterceptor(cacheInterceptor).cache(cache).build()
//    }

//    private fun isNetworkAvailable(app: Application): Boolean {
//        val cm = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val netInfo = cm.activeNetworkInfo
//        return netInfo != null && netInfo.isConnected
//    }


}



