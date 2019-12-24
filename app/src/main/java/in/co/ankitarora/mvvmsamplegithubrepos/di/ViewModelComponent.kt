package `in`.co.ankitarora.mvvmsamplegithubrepos.di

import `in`.co.ankitarora.mvvmsamplegithubrepos.viewModel.ListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, AppModule::class])
interface ViewModelComponent {
    fun inject(viewModel: ListViewModel)
}