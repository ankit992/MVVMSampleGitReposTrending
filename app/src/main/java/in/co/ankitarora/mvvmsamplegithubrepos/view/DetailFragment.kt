package `in`.co.ankitarora.mvvmsamplegithubrepos.view

import `in`.co.ankitarora.mvvmsamplegithubrepos.R
import `in`.co.ankitarora.mvvmsamplegithubrepos.databinding.DetailFragmentBinding
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

class DetailFragment : Fragment() {

    var gitRepoInfo: GitRepoInfo? = null
    private lateinit var databinding: DetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        databinding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false)
        return databinding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            gitRepoInfo = DetailFragmentArgs.fromBundle(it).gitRepoInfo
        }
        databinding.gitRepo = gitRepoInfo
    }
}
