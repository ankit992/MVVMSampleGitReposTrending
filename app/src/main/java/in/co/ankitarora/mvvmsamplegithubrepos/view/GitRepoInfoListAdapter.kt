package `in`.co.ankitarora.mvvmsamplegithubrepos.view

import `in`.co.ankitarora.mvvmsamplegithubrepos.R
import `in`.co.ankitarora.mvvmsamplegithubrepos.databinding.ItemGitRepoBinding
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class GitRepoInfoListAdapter(private val gitRepoInfoList: ArrayList<GitRepoInfo>) :
    RecyclerView.Adapter<GitRepoInfoListAdapter.GitRepoInfoListHolder>(), GitRepoItemClickListener {

    fun updateGitRepoList(newGitRepoList: List<GitRepoInfo>) {
        gitRepoInfoList.clear()
        gitRepoInfoList.addAll(newGitRepoList)
        notifyDataSetChanged()
    }

    class GitRepoInfoListHolder(var view: ItemGitRepoBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoInfoListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<ItemGitRepoBinding>(
                inflater,
                R.layout.item_git_repo,
                parent,
                false
            )
        return GitRepoInfoListHolder(view)
    }

    override fun getItemCount() = gitRepoInfoList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GitRepoInfoListHolder, position: Int) {
        holder.view.gitRepoInfo = gitRepoInfoList[position]
        holder.view.listener = this
    }

    override fun onClick(v: View) {
        gitRepoInfoList.find { gitRepoInfo -> gitRepoInfo.repoInfo?.url == v.tag }?.apply {
            val action = ListFragmentDirections.actionDetail(this)
            Navigation.findNavController(v).navigate(action)
        }
    }

}