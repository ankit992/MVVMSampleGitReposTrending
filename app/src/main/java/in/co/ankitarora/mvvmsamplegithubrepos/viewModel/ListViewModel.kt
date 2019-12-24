package `in`.co.ankitarora.mvvmsamplegithubrepos.viewModel

import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfoApiService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    val gitRepoInfoList by lazy { MutableLiveData<List<GitRepoInfo>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    private val disposable = CompositeDisposable()
    private val api = GitRepoInfoApiService()

    fun refresh() {
        getRepoInfoList()
    }

    private fun getRepoInfoList() {
        disposable.add(
            api.getGitRepoInfoList()
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
