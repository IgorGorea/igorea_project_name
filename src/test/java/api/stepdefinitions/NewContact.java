package api.stepdefinitions;

import api.actions.ApiActions;
import context.ObjectKeys;
import context.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewContact {
    protected static final Logger logger = LoggerFactory.getLogger(NewContact.class);
    protected final ApiActions apiActions = new ApiActions();
    protected final ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();


    @When("user creates a new contact through POST request using data:")
    public void userCreatesNewContactThroughPOSTUsingData(Map<String, String> params) {
        apiActions.postRequestAddContactWithParameters(params);
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
        boolean body = (boolean) scenarioContext.getData(ObjectKeys.FIRST_NAME_PRESENCE);
        logger.info("Is " + expContent + " present in all bodies?");
        assertTrue("Not all bodies contain " + expContent + " in Contact List", body);
    }

    @And("user creates a contact with valid parameters")
    public void userCreatesAContactWithValidParameters() {
        apiActions.postRequestAddContactWithParameters();
    }

    @When("user sends a DELETE request to contact")
    public void userSendsDELETERequestToContact() {
        logger.debug("The Contact ID to be deleted: " + scenarioContext.getData(ObjectKeys.NEW_CONTACT_ID).toString());
        apiActions.deleteContactById(scenarioContext.getData(ObjectKeys.NEW_CONTACT_ID).toString());
    }

    @Then("the DEL response status code should be {int}")
    public void verifyDeleteReqStatusCode(int expStatusCode) {
        assertEquals((int) scenarioContext.getData(ObjectKeys.DEL_CONTACT_STATUS_CODE), expStatusCode);
    }
}
