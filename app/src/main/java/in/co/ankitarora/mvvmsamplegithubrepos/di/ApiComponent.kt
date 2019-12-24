package `in`.co.ankitarora.mvvmsamplegithubrepos.di

import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfoApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: GitRepoInfoApiService)

}