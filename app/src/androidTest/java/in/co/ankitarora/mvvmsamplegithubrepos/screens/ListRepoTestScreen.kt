package `in`.co.ankitarora.mvvmsamplegithubrepos.screens

import `in`.co.ankitarora.mvvmsamplegithubrepos.R
import `in`.co.ankitarora.mvvmsamplegithubrepos.helper.RecyclerViewItemCountAssertion
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

class ListRepoTestScreen {
    fun validateListIsLoaded() {
        Espresso.onView(ViewMatchers.withId(R.id.gitRepoList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun validate(numberOfItems: Int) {
        Espresso.onView(ViewMatchers.withId(R.id.gitRepoList))
            .check(RecyclerViewItemCountAssertion(numberOfItems)).perform()
    }
}