package `in`.co.ankitarora.mvvmsamplegithubrepos.di

import `in`.co.ankitarora.mvvmsamplegithubrepos.viewModel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {
    fun inject(viewModel: ListViewModel)
}