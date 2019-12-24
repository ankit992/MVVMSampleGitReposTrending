package `in`.co.ankitarora.mvvmsamplegithubrepos.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface GitRepoInfoApi {
    @GET("developers?language=kotlin&since=weekly")
    fun getListOfTrendingGitRepos(): Single<List<GitRepoInfo>>
}