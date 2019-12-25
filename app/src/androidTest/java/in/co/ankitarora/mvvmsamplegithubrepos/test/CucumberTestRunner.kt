package `in`.co.ankitarora.mvvmsamplegithubrepos.test

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberInstrumentationCore


@CucumberOptions(
    features = arrayOf("features"),
    tags = arrayOf("@gitReposList"),
    glue = arrayOf("in.co.ankitarora.mvvmsamplegithubrepos.steps"),
    format = arrayOf(
        "pretty"),
    monochrome = true)
class CucumberTestRunner() : AndroidJUnitRunner() {

    private val instrumentationCore = CucumberInstrumentationCore(this)

    override fun onCreate(bundle: Bundle) {
        super.onCreate(bundle)
//        IdlingRegistry.getInstance().register(UriIdlingResourceSingleton.uriIdlingResource)
        instrumentationCore.create(bundle)
    }

    override fun onStart() {
        waitForIdleSync()
        instrumentationCore.start()
    }

    override fun onDestroy() {
        super.onDestroy()
//        IdlingRegistry.getInstance().unregister(UriIdlingResourceSingleton.uriIdlingResource)
    }

}