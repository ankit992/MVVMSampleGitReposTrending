package `in`.co.ankitarora.mvvmsamplegithubrepos.viewModel

import `in`.co.ankitarora.mvvmsamplegithubrepos.di.AppModule
import `in`.co.ankitarora.mvvmsamplegithubrepos.di.DaggerViewModelComponent
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel(app: Application) : AndroidViewModel(app) {

    constructor(app: Application, test: Boolean) : this(app) {
        injected = true
    }

    private var injected = false;
    val gitRepoInfoList by lazy { MutableLiveData<List<GitRepoInfo>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    private val disposable = CompositeDisposable()

    @Inject
    lateinit var api: Single<List<GitRepoInfo>>

    fun refresh() {
        getRepoInfoList()
    }

    init {
        if (!injected)
            DaggerViewModelComponent.builder().appModule(AppModule(getApplication())).build().inject(this)
    }

    private fun getRepoInfoList() {
        disposable.add(
            api
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<GitRepoInfo>>() {
                    override fun onSuccess(list: List<GitRepoInfo>) {
                        gitRepoInfoList.value = list
                        loadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        //the line below needs to log it somewhere in future
                        e.printStackTrace()
                        gitRepoInfoList.value = null
                        loading.value = false
                        loadError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        disposable.clear()
    }

}
