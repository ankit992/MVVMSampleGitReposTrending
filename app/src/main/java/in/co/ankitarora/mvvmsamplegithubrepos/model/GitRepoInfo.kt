package `in`.co.ankitarora.mvvmsamplegithubrepos.model

import com.google.gson.annotations.SerializedName

data class GitRepoInfo(
    @SerializedName("username") val username: String?, @SerializedName("name") val name: String?, @SerializedName(
        "type"
    ) val type: String?, @SerializedName("url") val userUrl: String?, @SerializedName("avatar") val avatarUrl: String?, @SerializedName(
        "repo"
    ) val repoInfo: RepoInfo?
)

data class RepoInfo(
    @SerializedName("name") val name: String?, @SerializedName("description") val description: String?, @SerializedName(
        "url"
    ) val url: String?
)
