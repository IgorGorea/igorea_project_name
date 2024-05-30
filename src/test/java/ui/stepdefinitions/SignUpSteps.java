package ui.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utililities.ConfigReader;
import context.ObjectKeys;
import context.ScenarioContext;
import ui.pages.SignUpPage;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SignUpSteps {
    protected static final Logger logger = LoggerFactory.getLogger(SignUpSteps.class);
    private ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();
    private final ConfigReader configReader = new ConfigReader();
    private final SignUpPage signUpPage = new SignUpPage( scenarioContext.getData(ObjectKeys.WEB_DRIVER));

    @Given("sign up page is opened")
    public void signUpPageIsOpened() {
        signUpPage.openPage( scenarioContext.getData(ObjectKeys.WEB_DRIVER), configReader.getProperty("signup.page"));
    }

    @When("user submits valid credentials")
    public void userSubmitsValidCredentials() {
        logger.info("The next step will use these random credentials:");
        signUpPage.submitValidCredentials();
    }

    @When("user registers with the following credentials:")
    public void userRegistersWithTheFollowingCredentials(Map<String, String> credTable) {
        signUpPage.submitWithSignUpCredentials(credTable);
    }

    @Then("user is successfully created")
    public void userIsSuccessfullyCreated() {
        MatcherAssert.assertThat(signUpPage.logoutIsPresent(), is(true));
    }

    @When("user cancels with the following credentials:")
    public void userCancelsWithTheFollowingCredentials(Map<String, String> credTable) {
        logger.info("The next step will use these random credentials:");
        signUpPage.cancelWithSignUpCredentials(credTable);
    }

    @Then("the main page is opened")
    public void theMainPageIsOpened() {
        assertThat(signUpPage.signUpIsPresent(), is("Sign up"));
        signUpPage.assertThatPageIsOpened(scenarioContext.getConfigReader().getProperty("login.page"));
    }

    @Then("the error on sign up page is displayed:")
    public void theErrorOnSignUpPageIsDisplayed(Map<String, String> credTable) {
        assertThat(signUpPage.errorMessageIsPresent(), is("User validation failed: " + credTable.get("ErrorMessage")));
    }

}
