package `in`.co.ankitarora.mvvmsamplegithubrepos

import `in`.co.ankitarora.mvvmsamplegithubrepos.di.ApiModule
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import io.reactivex.Single

class ApiModuleTest(val mockApiService: Single<List<GitRepoInfo>>) : ApiModule() {
    override fun provideGitRepoInfoListApiService(app: App): Single<List<GitRepoInfo>> {
        return mockApiService
    }
}