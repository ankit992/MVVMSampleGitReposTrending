package `in`.co.ankitarora.mvvmsamplegithubrepos.view

import `in`.co.ankitarora.mvvmsamplegithubrepos.R
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import `in`.co.ankitarora.mvvmsamplegithubrepos.viewModel.ListViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment() {

    private val listAdapter = GitRepoInfoListAdapter(arrayListOf())

    private val gitRepoInfoListDataObserver = Observer<List<GitRepoInfo>> { list ->
        list?.let {
            gitRepoList.visibility = View.VISIBLE
            listAdapter.updateGitRepoList(list)
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> { loading ->
        loadingView.visibility = if(loading) View.VISIBLE else View.GONE
        if(loading){
            listError.visibility = View.GONE
            gitRepoList.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        listError.visibility = if(isError) View.VISIBLE else View.GONE
    }

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        viewModel.gitRepoInfoList.observe(this, gitRepoInfoListDataObserver)
        viewModel.loading.observe(this, loadingLiveDataObserver)
        viewModel.loadError.observe(this, errorLiveDataObserver)

        viewModel.refresh()
        gitRepoList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }

        refreshLayout.setOnRefreshListener {
            gitRepoList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }
}
