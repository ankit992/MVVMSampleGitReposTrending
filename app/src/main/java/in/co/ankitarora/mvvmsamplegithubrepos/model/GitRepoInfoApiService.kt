package `in`.co.ankitarora.mvvmsamplegithubrepos.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitRepoInfoApiService {
    private val BASE_URL = "https://github-trending-api.now.sh/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(GitRepoInfoApi::class.java)

    fun getGitRepoInfoList(): Single<List<GitRepoInfo>>{
        return api.getListOfTrendingGitRepos()
    }
}