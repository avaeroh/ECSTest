package stepdefs;

import base.Test_base;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.TestPage;

import static pages.TestPage.BUTTON_SUBMIT;

public class TestPageStepDefs extends Test_base {

    private TestPage testPage;

    public TestPageStepDefs() {
        testPage = new TestPage(driver);
    }

    @Given("I go to the start page")
    public void iGoToTheStartPage() {
        testPage.setUp();
        testPage.navigateToHomePage();
    }

    @When("^I click on render the challenge$")
    public void iClickOnRenderTheChallenge() {
        testPage.waitForPageToLoad();
        testPage.clickRenderTheChallenge();
    }

    @And("^I save each row of the challenge as an array$")
    public void iSaveEachRowOfTheChallengeAsAnArray() {
        testPage.fillArrays();
    }

    @And("^I calculate the equilibrium index of the arrays$")
    public void iCalculateTheEquilibriumIndexOfTheArrays() {
        testPage.saveEquilibriumIndexes();
    }

    @Then("^I submit the answers$")
    public void iSubmitTheAnswers() {
        testPage.fillAnswerBoxes();
//        testPage.click(BUTTON_SUBMIT);
    }

}

