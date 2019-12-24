package `in`.co.ankitarora.mvvmsamplegithubrepos

import `in`.co.ankitarora.mvvmsamplegithubrepos.di.DaggerViewModelComponent
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfoApiService
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
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class ListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var gitRepoInfoApiService: GitRepoInfoApiService

    private lateinit var listViewModel: ListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        listViewModel = ListViewModel(gitRepoInfoApiService)
        DaggerViewModelComponent.builder()
            .apiModule(ApiModuleTest(gitRepoInfoApiService))
            .build().inject(listViewModel)
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
        val testSingle = Single.just(gitRepoInfoList)
        Mockito.`when`(gitRepoInfoApiService.getGitRepoInfoList()).thenReturn(testSingle)
        listViewModel.refresh()
        assertEquals(1,listViewModel.gitRepoInfoList.value?.size)
        assertEquals(false, listViewModel.loadError.value)
        assertEquals(false, listViewModel.loading.value)
    }

    @Test
    fun getGitRepoInfoListFailure(){
        val testSingle: Single<List<GitRepoInfo>> = Single.error(Throwable())
        Mockito.`when`(gitRepoInfoApiService.getGitRepoInfoList()).thenReturn(testSingle)
        listViewModel.refresh()
        assertEquals(null,listViewModel.gitRepoInfoList.value)
        assertEquals(true, listViewModel.loadError.value)
        assertEquals(false, listViewModel.loading.value)
    }


}