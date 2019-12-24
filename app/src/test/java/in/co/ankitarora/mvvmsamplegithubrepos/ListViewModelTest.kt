package `in`.co.ankitarora.mvvmsamplegithubrepos

import `in`.co.ankitarora.mvvmsamplegithubrepos.di.AppModule
import `in`.co.ankitarora.mvvmsamplegithubrepos.di.DaggerViewModelComponent
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import `in`.co.ankitarora.mvvmsamplegithubrepos.viewModel.ListViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class ListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    lateinit var gitRepoInfoApiService: Single<List<GitRepoInfo>>

    private lateinit var listViewModel: ListViewModel

    @Mock
    lateinit var app: App

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        listViewModel = ListViewModel(app)
    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }
        }
        RxJavaPlugins.setIoSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }

    @Test
    fun getGitRepoInfoListSuccess(){
        val gitRepoInfo = GitRepoInfo("username",null,null,null,null,null)
        val gitRepoInfoList = listOf(gitRepoInfo)
        setNetworkResponse(Single.just(gitRepoInfoList))
        listViewModel.refresh()
        assertEquals(1,listViewModel.gitRepoInfoList.value?.size)
        assertEquals(false, listViewModel.loadError.value)
        assertEquals(false, listViewModel.loading.value)
    }



    @Test
    fun getGitRepoInfoListFailure(){
        setNetworkResponse( Single.error(Throwable()))
        listViewModel.refresh()
        assertEquals(null,listViewModel.gitRepoInfoList.value)
        assertEquals(true, listViewModel.loadError.value)
        assertEquals(false, listViewModel.loading.value)
    }


    private fun setNetworkResponse(response: Single<List<GitRepoInfo>>) {
        gitRepoInfoApiService = response
        DaggerViewModelComponent.builder()
            .apiModule(ApiModuleTest(gitRepoInfoApiService))
            .appModule(AppModule(app))
            .build().inject(listViewModel)
    }

}