package `in`.co.ankitarora.mvvmsamplegithubrepos

import `in`.co.ankitarora.mvvmsamplegithubrepos.di.ApiModule
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfoApiService

class ApiModuleTest(val mockApiService: GitRepoInfoApiService) : ApiModule() {
    override fun provideGitRepoInfoListApiService(): GitRepoInfoApiService {
        return mockApiService
    }
}