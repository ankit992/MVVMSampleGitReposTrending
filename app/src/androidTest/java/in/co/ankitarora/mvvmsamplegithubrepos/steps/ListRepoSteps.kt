package `in`.co.ankitarora.mvvmsamplegithubrepos.steps

import `in`.co.ankitarora.mvvmsamplegithubrepos.screens.ListRepoTestScreen
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import cucumber.runtime.java.StepDefAnnotation
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@StepDefAnnotation
class ListRepoSteps(private val listRepoScreen: ListRepoTestScreen){

    @Given("I am a user")
    fun i_am_a_user(){

    }

    @Then("I should see trending git repos loaded")
    fun i_should_see_a_go_to_template_chooser_button() {
        listRepoScreen.validateListIsLoaded()
    }

    @Then("The List should have 4 items in it")
    fun the_list_should_have_4_items_in_it(){
        listRepoScreen.validate(4)
    }

    @When("I click on 1st Item in List")
    fun i_click_on_3rd_item_in_list(){
        listRepoScreen.clickItemNumber(0)
    }


}