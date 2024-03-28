package api.stepdefinitions;

import api.actions.ApiActions;
import api.utililities.ContextEnum;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.actions.DriverActions;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewContact extends DriverActions {
    protected static final Logger logger = LogManager.getLogger();
    protected Response response;
    ApiActions apiActions = new ApiActions();

    @Given("check the server is up")
    public void healthCheckTheServerIsUp() {
        //In real project will be changed with HealthCheck
        apiActions.getContactListHealthCheck();
    }

    @When("user sends a POST request to contacts with parameters in body:")
    public void userSendsPOSTRequestToContactsWithParametersInBody(Map<String, String> params) {
        apiActions.postRequestAddContactWithParameters(params);
    }

    @Then("the POST response status code should be {int}")
    public void verifyPOSTStatusCode(int expStatusCode) {
        assertEquals(ContextEnum.POST_STATUS_CODE.getIntValue(), expStatusCode);
    }

    @When("user sends a GET request to contact list")
    public void sendGETContactListRequest() {
        apiActions.getContactList();
    }

    @Then("the GET contact list response status code should be {int}")
    public void verifyContactListStatusCode(int expStatusCode) {
        assertEquals(apiActions.getContactListStatusCode(), expStatusCode);
    }

    @Then("the contact list contains {string} in all response bodies")
    public void verifyGETResponseBodyContainsId(String expContent) {
        boolean body = ContextEnum.FIRST_NAME_PRESENCE.getBoolValue();
        logger.info("Is " + expContent + " present in all bodies?");
        assertTrue("Not all bodies contain " + expContent + " in Contact List", body);
    }


    @When("user sends a DELETE request to contact")
    public void userSendsDELETERequestToContact() {
        logger.debug(ContextEnum.NEW_CONTACT_ID.getValue());
        apiActions.deleteContactById(ContextEnum.NEW_CONTACT_ID.getValue());
    }

    @Then("the DEL response status code should be {int}")
    public void verifyDeleteReqStatusCode(int expStatusCode) {
        assertEquals(ContextEnum.DEL_STATUS_CODE.getIntValue(), expStatusCode);
    }

    @Then("the response body contains {string}")
    public void verifyResponseBodyContainsSuccess(String expContent) {
        String body = apiActions.getResponseBody();
        logger.info("Is " + expContent + " present in the body: " + body.contains(expContent));
        assertTrue("Response body does not contain " + expContent, body.contains(expContent));
    }
}
