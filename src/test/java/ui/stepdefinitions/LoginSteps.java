package ui.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.awaitility.Awaitility;
import org.junit.Assert;
import ui.actions.DriverActions;
import ui.actions.LoginStepsActions;
import ui.context.ConfigReader;
import io.cucumber.java.en.Given;
import ui.pages.LoginPage;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginSteps extends DriverActions {
    private final LoginStepsActions loginStepsActions = new LoginStepsActions();

//    @Given("log in page is opened in chrome")
//    public void logInPageIsOpenedInChrome() {
//        ConfigReader configReader = new ConfigReader();
//        String loginPage = configReader.getProperty("login.page");
//        openPage(loginPage);
//    }
//
//    @And("user logs in with the following credentials:")
//    public void userLogsInWithTheFollowingCredential(Map<String, String> credTable) {
//        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> LoginPage.login_submit_button_xpath.isDisplayed());;
//        loginStepsActions.introducingLoginCredentials(credTable);
//        loginStepsActions.loginPage.press_loginPage_submit_button();
//    }
//
//    @Then("the Contact List page is opened")
//    public void theContactListPageIsOpened() {
//        Awaitility.await().atMost(5, TimeUnit.SECONDS).until(() -> LoginPage.logout_button_xpath.isDisplayed());
//        String expUrl="https://thinking-tester-contact-list.herokuapp.com/contactList";
//        Assert.assertEquals(actUrl(),expUrl);
//    }

    @Given("the Contact List page is opened with the following credentials:")
    public void theContactListPageIsOpenedWithTheFollowingCredentials(Map<String, String> credTable) {
        String loginPage = configReader.getProperty("login.page");
        openPage(loginPage);
        loginStepsActions.introducingLoginCredentials(credTable);
        loginStepsActions.loginPage.press_loginPage_submit_button();
    }
}
