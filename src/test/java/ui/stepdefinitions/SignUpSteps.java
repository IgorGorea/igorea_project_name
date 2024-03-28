package ui.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Transpose;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.actions.DriverActions;
import ui.actions.SignUpStepsActions;
import ui.context.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import ui.pages.SignUpPage;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

//TODO Css selector plugin check and apply if wanted
public class SignUpSteps extends DriverActions {


    private final SignUpStepsActions signUpStepsActions = new SignUpStepsActions();

    @Given("sign up page is opened in chrome")
    public void signUpPageIsOpenedInChrome() {
        String signUpPageUrl = configReader.getProperty("signup.page");
        openPage(signUpPageUrl);
    }

    @When("user registers with the valid credentials")
    public void userRegistersWithTheValidCredentials() {
        logger.info("The next step will use these random credentials:");
        signUpStepsActions.introducingRandomCredentials();
        //TODO Refactor and disrupt action circle
        signUpStepsActions.signUpPage.signUpSubmit();
        //TODO add log for credentials
    }

    @When("user registers with the following credentials:")
    public void userRegistersWithTheFollowingCredentials(Map<String, String> credTable) {
        signUpStepsActions.introducingCredentials(credTable);
        signUpStepsActions.signUpPage.signUpSubmit();
    }

    @Then("the user is successfully created")
    public void theUserIsSuccessfullyCreated() {
        MatcherAssert.assertThat(SignUpPage.logout_button_is_present(), is("Logout"));

    }

    @When("user cancels with the following credentials:")
    public void userCancelsWithTheFollowingCredentials(Map<String, String> credTable) {
        logger.info("The next step will use these random credentials:");
        signUpStepsActions.introducingCredentials(credTable);
        signUpStepsActions.signUpPage.press_signUp_cancel_button();
    }

    @Then("the main page is opened")
    public void theMainPageIsOpened() {
        assertThat(SignUpPage.signUp_button_is_present(), is("Sign up"));
        logger.info(abstractDriver.getCurrentUrl());
    }

    @Then("the error on sign up page appeared:")
    public void theErrorOnSignUpPageAppeared(Map<String, String> credTable) {
        assertThat(SignUpPage.error_message_is_present(), is("User validation failed: " + credTable.get("ErrorMessage")));

    }

}
