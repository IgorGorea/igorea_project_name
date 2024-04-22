package ui.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import ui.utils.ConfigReader;
import ui.context.ObjectKeys;
import ui.context.ScenarioContext;
import ui.pages.LoginPage;

import static org.hamcrest.Matchers.is;

public class LoginSteps {
    private ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();
    private final ConfigReader configReader = new ConfigReader();
    private final LoginPage loginPage = new LoginPage((WebDriver) scenarioContext.getData(ObjectKeys.WEB_DRIVER));

    @Given("user is created with valid credentials")
    public void userIsCreatedWithValidCredentials() {
        loginPage.openPage((WebDriver) scenarioContext.getData(ObjectKeys.WEB_DRIVER), configReader.getProperty("signup.page"));
        loginPage.submitValidCredentials();
    }

    @Given("user pressed the logout button")
    public void userPressedTheLogoutButton() {
        loginPage.logout();
    }

    @And("login page is opened in chrome")
    public void loginPageIsOpenedInChrome() {

    }

    @When("user submits created credentials")
    public void userSubmitsCreatedCredentials() {
        loginPage.submitLoginCredentials();
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        MatcherAssert.assertThat(loginPage.logoutIsPresent(), is("Logout"));
    }


    @When("user submits created credentials on login page")
    public void userSubmitsCreatedCredentialsOnLoginPage() {
        loginPageIsOpenedInChrome();
        loginPage.submitLoginCredentials();
    }

    @Then("there is created contact with {string}")
    public void thereIsCreatedContactWith(String nameCheck) {
        MatcherAssert.assertThat(loginPage.firstContactName(), is(nameCheck));
    }

}
