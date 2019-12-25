package `in`.co.ankitarora.mvvmsamplegithubrepos.screens

import `in`.co.ankitarora.mvvmsamplegithubrepos.R
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

class RepoDetailTestScreen {
    fun validateScreenIsLoaded() {
        Espresso.onView(ViewMatchers.withId(R.id.repo_detail_layout))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun validateRepoNameShown(repoName: String) {
        Espresso.onView(ViewMatchers.withText(repoName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}