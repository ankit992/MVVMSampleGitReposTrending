package `in`.co.ankitarora.mvvmsamplegithubrepos.di

import `in`.co.ankitarora.mvvmsamplegithubrepos.App
import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Provides
    fun providesApp(): App = app
}