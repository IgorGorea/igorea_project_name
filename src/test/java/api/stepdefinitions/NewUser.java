package api.stepdefinitions;

import api.actions.ApiActions;
import api.actions.UtilActions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import context.ObjectKeys;
import context.ScenarioContext;

import static org.junit.Assert.assertEquals;

public class NewUser {
    protected final ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();
    protected final ApiActions apiActions = new ApiActions();
    protected final UtilActions utilActions = new UtilActions();

    @Given("the server is up")
    public void healthCheckTheServerIsUp() {
        //In real project will be changed with HealthCheck
        apiActions.getContactListHealthCheck();
    }

    @Given("admin has created a new user")
    @When("admin creates a new user")
    public void adminCreatesANewUser() {
        apiActions.postRequestAddUserWithParameters(utilActions.newUserCredentials());
    }

    @Then("the POST response status code should be {int}")
    public void verifyPOSTStatusCode(int expStatusCode) {
        assertEquals((int) scenarioContext.getData(ObjectKeys.POST_STATUS_CODE), expStatusCode);
    }

    @Then("the response body contains {string}")
    public void verifyResponseBodyContainsSuccess(String expContent) {
        apiActions.assertThatBodyContains(expContent);
    }
}
