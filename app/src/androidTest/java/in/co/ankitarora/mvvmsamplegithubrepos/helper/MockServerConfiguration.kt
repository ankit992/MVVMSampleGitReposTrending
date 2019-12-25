package `in`.co.ankitarora.mvvmsamplegithubrepos.helper

import `in`.co.ankitarora.mvvmsamplegithubrepos.model.GitRepoInfo
import `in`.co.ankitarora.mvvmsamplegithubrepos.model.RepoInfo
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.lang.Exception

fun configureMockServer(mockWebServer: MockWebServer) {
    try {
        Thread {
            mockWebServer.start(8080)
        }.start()

    } catch (e: Exception) {
        e.printStackTrace()
    }
    mockWebServer.enqueue(
        MockResponse().setResponseCode(200).setBody(
            Gson().toJson(
                listOf(
                    GitRepoInfo(
                        "username1",
                        "name1",
                        "type",
                        "userUrl",
                        null,
                        RepoInfo("reponame", "desc", "dummyRepoUrl")
                    ),
                    GitRepoInfo(
                        "username2",
                        "name2",
                        "type",
                        "userUrl",
                        null,
                        RepoInfo("reponame", "desc", "dummyRepoUrl")
                    ),
                    GitRepoInfo(
                        "username3",
                        "name3",
                        "type",
                        "userUrl",
                        null,
                        RepoInfo("reponame", "desc", "dummyRepoUrl")
                    ),
                    GitRepoInfo(
                        "username4",
                        "name4",
                        "type",
                        "userUrl",
                        null,
                        RepoInfo("reponame", "desc", "dummyRepoUrl")
                    )
                )
            )
        )
    )


}