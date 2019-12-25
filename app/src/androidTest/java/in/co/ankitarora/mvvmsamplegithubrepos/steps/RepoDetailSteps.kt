package `in`.co.ankitarora.mvvmsamplegithubrepos.steps

import `in`.co.ankitarora.mvvmsamplegithubrepos.screens.RepoDetailTestScreen
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import cucumber.api.java.en.Then
import cucumber.runtime.java.StepDefAnnotation
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@StepDefAnnotation
class RepoDetailSteps(private val repoDetailTestScreen: RepoDetailTestScreen) {
    @Then("I should see the RepoDetailScreen")
    fun i_should_see_the_repo_detail_screen() {
        repoDetailTestScreen.validateScreenIsLoaded()
    }

    @Then("I should see the RepoName1 on screen")
    fun i_should_see_3rd_repo_name_on_screen(){
        repoDetailTestScreen.validateRepoNameShown("reponame1")
    }
}