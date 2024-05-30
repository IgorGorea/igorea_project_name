package ui.stepdefinitions;

import context.ObjectKeys;
import context.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import ui.pages.LoginPage;
import utililities.ConfigReader;

import static org.hamcrest.Matchers.is;

public class LoginSteps {
    private ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();
    private final ConfigReader configReader = new ConfigReader();
    private final LoginPage loginPage = new LoginPage(scenarioContext.getData(ObjectKeys.WEB_DRIVER));

    @Given("user is created with valid credentials")
    public void userIsCreatedWithValidCredentials() {
        loginPage.openPage(scenarioContext.getData(ObjectKeys.WEB_DRIVER), configReader.getProperty("signup.page"));
        loginPage.submitValidCredentials();
    }

    @Given("user pressed the logout button")
    public void userPressedTheLogoutButton() {
        loginPage.logout();
    }

    @When("user submits created credentials")
    public void userSubmitsCreatedCredentials() {
        loginPage.submitLoginCredentials();
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {
        MatcherAssert.assertThat(loginPage.logoutIsPresent(), is(true));
    }


    @When("user submits created credentials on login page")
    public void userSubmitsCreatedCredentialsOnLoginPage() {
        loginPage.submitLoginCredentials();
    }

    @Then("there is created contact with {string}")
    public void thereIsCreatedContactWith(String nameToCheck) {
        MatcherAssert.assertThat(loginPage.getFirstContactName(), is(nameToCheck));
    }

}
