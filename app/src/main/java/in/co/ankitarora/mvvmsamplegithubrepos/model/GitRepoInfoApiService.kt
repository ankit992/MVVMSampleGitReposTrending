package `in`.co.ankitarora.mvvmsamplegithubrepos.model

import `in`.co.ankitarora.mvvmsamplegithubrepos.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class GitRepoInfoApiService {
    @Inject
    lateinit var api: GitRepoInfoApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getGitRepoInfoList(): Single<List<GitRepoInfo>> {

        return api.getListOfTrendingGitRepos()
    }
}