package `in`.co.ankitarora.mvvmsamplegithubrepos.test

import `in`.co.ankitarora.mvvmsamplegithubrepos.helper.configureMockServer
import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import cucumber.api.CucumberOptions
import cucumber.api.android.CucumberInstrumentationCore
import okhttp3.mockwebserver.MockWebServer


@CucumberOptions(
    features = arrayOf("features"),
    tags = arrayOf("@gitReposList"),
    glue = arrayOf("in.co.ankitarora.mvvmsamplegithubrepos.steps"),
    format = arrayOf(
        "pretty"),
    monochrome = true)
class CucumberTestRunner() : AndroidJUnitRunner() {

    private val instrumentationCore = CucumberInstrumentationCore(this)
    lateinit var mockWebServer: MockWebServer
    override fun onCreate(bundle: Bundle) {
        super.onCreate(bundle)
        mockWebServer = MockWebServer()
        configureMockServer(mockWebServer)
        instrumentationCore.create(bundle)
    }

    override fun onStart() {
        waitForIdleSync()
        instrumentationCore.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mockWebServer.shutdown()
    }

}