package `in`.co.ankitarora.mvvmsamplegithubrepos.view

import `in`.co.ankitarora.mvvmsamplegithubrepos.R
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import `in`.co.ankitarora.mvvmsamplegithubrepos.util.getProgressDrawable
import `in`.co.ankitarora.mvvmsamplegithubrepos.util.loadImage
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_git_repo.view.*

class GitRepoInfoListAdapter(private val gitRepoInfoList: ArrayList<GitRepoInfo>) :
    RecyclerView.Adapter<GitRepoInfoListAdapter.GitRepoInfoListHolder>() {

    fun updateGitRepoList(newGitRepoList: List<GitRepoInfo>) {
        gitRepoInfoList.clear()
        gitRepoInfoList.addAll(newGitRepoList)
        notifyDataSetChanged()
    }

    class GitRepoInfoListHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoInfoListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_git_repo, parent, false)
        return GitRepoInfoListHolder(view)
    }

    override fun getItemCount() = gitRepoInfoList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GitRepoInfoListHolder, position: Int) {
        holder.view.userName.text = "by ${gitRepoInfoList[position].username ?: "username not found"}"
        holder.view.repositoryName.text =
            gitRepoInfoList[position].repoInfo?.name ?: "RepoName not found"
        holder.view.avatarImage.loadImage(
            gitRepoInfoList[position].avatarUrl,
            getProgressDrawable(holder.view.context)
        )
    }

}