package `in`.co.ankitarora.mvvmsamplegithubrepos.di

import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfoApi
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfoApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://github-trending-api.now.sh/"

    @Provides
    fun provideGitRepoInfoListApi(): GitRepoInfoApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GitRepoInfoApi::class.java)
    }

    @Provides
    fun provideGitRepoInfoListApiService(): GitRepoInfoApiService{
        return GitRepoInfoApiService()
    }
}