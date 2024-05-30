package api.actions;

import api.dtos.requests.ContactRequest;
import api.dtos.requests.UserRequest;
import api.dtos.responses.ContactResponse;
import api.dtos.responses.UserResponse;
import context.ObjectKeys;
import context.ScenarioContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utililities.ConfigReader;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class ApiActions {
    protected ConfigReader configReader = new ConfigReader();
    protected static final Logger logger = LoggerFactory.getLogger(ApiActions.class);
    protected ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();
    private Response response;
    private final UtilActions utilActions = new UtilActions();

    private RequestSpecification responseMethodWOToken() {
        return given()
                .when();
    }

    private RequestSpecification responseMethod() {
        String token = ((UserResponse) scenarioContext.getData((ObjectKeys.NEW_USER))).getToken();
        return responseMethod(token);
    }

    private RequestSpecification responseMethod(String token) {
        return given().header("Authorization", "Bearer " + token)
                .when();
    }

    private Response bodyPostMethod(ContactRequest reqBody){
        return responseMethod()
                .contentType(ContentType.JSON)
                .body(reqBody)
                .log()
                .all()
                .post();
    }
    private void baseUriSetting(String pathFromProperties) {
        baseURI = configReader.getProperty("baseURI");
        basePath = configReader.getProperty(pathFromProperties);
        logger.debug("Current URI and base path: " + baseURI + basePath);
    }

    public void getContactListHealthCheck() {
        try {
            String token = configReader.getProperty("token.bearer");
            baseUriSetting("baseContactsPath");

            response = responseMethod(token)
                    .get();

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                logger.info("GET request to contacts endpoint was successful with status code: " + response.getStatusCode());
            } else {
                logger.error("GET request to contacts endpoint failed. Status code: " + response.getStatusCode());
            }
        } catch (NullPointerException e) {
            logger.error("An exception occurred while sending GET request to contacts endpoint: " + e.getMessage());
            throw new NullPointerException();
        }
    }

    public void getContactList() {
        baseUriSetting("baseContactsPath");

        response = responseMethod()
                .get();
        response.then().log().all();

        List<String> firstNames = response.jsonPath().getList("firstName");
        boolean allHaveFirstName = firstNames.stream().allMatch(Objects::nonNull);

        scenarioContext.setData(ObjectKeys.FIRST_NAME_PRESENCE, allHaveFirstName);
        scenarioContext.setData(ObjectKeys.GET_STATUS_CODE, response.getStatusCode());
    }

    public int getContactListStatusCode() {
        int gStCode = scenarioContext.getData(ObjectKeys.GET_STATUS_CODE);
        logger.debug(String.valueOf(gStCode));
        return gStCode;
    }

    public void assertThatBodyContains(String expContent) {
        String body = ((Response) scenarioContext.getData(ObjectKeys.RESPONSE)).getBody().asString();
        assertTrue("Response body does not contain " + expContent, body.contains(expContent));
        logger.info("Is " + expContent + " present in the body: " + body.contains(expContent));
    }

    public void postRequestAddContactWithParameters(Map<String, String> params) {
        baseUriSetting("baseContactsPath");

        ContactRequest reqBody = new ContactRequest();
        utilActions.newContactCredentials(reqBody, params);
        response = bodyPostMethod(reqBody);

        response.then().log().all();
        String contactId = utilActions.getParamFromJson(response, "_id");
        ContactResponse respBody = response.body().as(ContactResponse.class);
        String contactIdPojo = respBody.getId();
        scenarioContext.setData(ObjectKeys.NEW_CONTACT_ID, contactId);
        logger.info("Json parser got the value: " + contactId + "and id gotten using Pojo object is: " + contactIdPojo);
        scenarioContext.setData(ObjectKeys.POST_STATUS_CODE, response.getStatusCode());
        logger.debug("POST Status code:" + response.getStatusCode());
        scenarioContext.setData(ObjectKeys.RESPONSE, response.then().extract().response());
    }

    public void postRequestAddContactWithParameters() {
        baseUriSetting("baseContactsPath");
        ContactRequest reqBody = new ContactRequest();
        utilActions.newContactCredentials(reqBody);
        response = bodyPostMethod(reqBody);

        response.then().log().all();
        ContactResponse respBody = response.body().as(ContactResponse.class);
        logger.debug("Response body: " + respBody.toString());
        String contactId = utilActions.getParamFromJson(response, "_id");
        scenarioContext.setData(ObjectKeys.NEW_CONTACT_ID, contactId);
        logger.info("Json parser got the value: " + contactId);
        scenarioContext.setData(ObjectKeys.POST_STATUS_CODE, response.getStatusCode());
        logger.debug("POST Status code:" + response.getStatusCode());
        scenarioContext.setData(ObjectKeys.RESPONSE, response.then().extract().response());
    }

    public void deleteContactById(String ContactId) {
        baseUriSetting("baseContactsPath"+ "/" + ContactId);
        logger.debug("Contact ID to be deleted:" + ContactId);
        response = responseMethod()
                .log()
                .all()
                .delete();
        response.then().log().all();
        logger.debug("DelStatCode:" + response.getStatusCode());
        scenarioContext.setData(ObjectKeys.DEL_CONTACT_STATUS_CODE, response.getStatusCode());
        scenarioContext.setData(ObjectKeys.RESPONSE, response.then().extract().response());
    }

    public void postRequestAddUserWithParameters() {
        baseUriSetting("baseUsersPath");
        UserRequest reqBody = new UserRequest();
        utilActions.newUserCredentials(reqBody);
        response = responseMethodWOToken()
                .contentType(ContentType.JSON)
                .body(reqBody)
                .log()
                .all()
                .post();
        response.then().log().all();
        UserResponse respBody = response.body().as(UserResponse.class);
        String token = respBody.getToken();
        scenarioContext.setData(ObjectKeys.NEW_USER, respBody);
        scenarioContext.setData(ObjectKeys.USER_EMAIL, respBody.getUser().getEmail());
        logger.debug("POST Status code:" + response.getStatusCode());
        scenarioContext.setData(ObjectKeys.POST_STATUS_CODE, response.getStatusCode());
        scenarioContext.setData(ObjectKeys.RESPONSE, response.then().extract().response());

        logger.info("Json parser got the token bearer value: " + token);
    }

    public void deleteUserByToken() {
        baseUriSetting("baseMeUserPath");
        logger.debug("Will be deleted " + ((UserResponse) scenarioContext.getData((ObjectKeys.NEW_USER))).getUser().getFirstName()
                + " with token:" + ((UserResponse) scenarioContext.getData((ObjectKeys.NEW_USER))).getToken());
        response = responseMethod()
                .log()
                .all()
                .delete();
        response.then().log().all();
        scenarioContext.setData(ObjectKeys.DEL_USER_STATUS_CODE, response.getStatusCode());
    }
}
