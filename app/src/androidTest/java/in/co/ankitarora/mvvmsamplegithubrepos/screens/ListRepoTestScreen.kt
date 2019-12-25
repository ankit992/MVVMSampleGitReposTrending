package `in`.co.ankitarora.mvvmsamplegithubrepos.screens

import `in`.co.ankitarora.mvvmsamplegithubrepos.R
import `in`.co.ankitarora.mvvmsamplegithubrepos.helper.RecyclerViewItemCountAssertion
import `in`.co.ankitarora.mvvmsamplegithubrepos.helper.nthChildOf
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId

class ListRepoTestScreen {
    fun validateListIsLoaded() {
        Espresso.onView(withId(R.id.gitRepoList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun validate(numberOfItems: Int) {
        Espresso.onView(withId(R.id.gitRepoList))
            .check(RecyclerViewItemCountAssertion(numberOfItems)).perform()
    }

    fun clickItemNumber(itemNumber: Int) {
        Espresso.onView(nthChildOf(withId(R.id.gitRepoList), itemNumber - 1))
            .perform(ViewActions.click())
    }


}