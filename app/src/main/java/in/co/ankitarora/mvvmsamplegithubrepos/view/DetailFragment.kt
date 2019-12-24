package `in`.co.ankitarora.mvvmsamplegithubrepos.view

import `in`.co.ankitarora.mvvmsamplegithubrepos.R
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import `in`.co.ankitarora.mvvmsamplegithubrepos.util.getProgressDrawable
import `in`.co.ankitarora.mvvmsamplegithubrepos.util.loadImage
import `in`.co.ankitarora.mvvmsamplegithubrepos.viewModel.DetailViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    var gitRepoInfo: GitRepoInfo? = null
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            gitRepoInfo = DetailFragmentArgs.fromBundle(it).gitRepoInfo
        }
        context?.let {
            userImage.loadImage(gitRepoInfo?.avatarUrl, getProgressDrawable(it))
        }

        userName.text = "by ${gitRepoInfo?.username}"
        name.text = gitRepoInfo?.name
        gitRepoDescription.text = gitRepoInfo?.repoInfo?.description
        gitRepoName.text = gitRepoInfo?.repoInfo?.name
        gitRepoUrl.text = gitRepoInfo?.repoInfo?.url
        userUrl.text = gitRepoInfo?.userUrl

    }
}
