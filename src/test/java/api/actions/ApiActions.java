package api.actions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import context.ObjectKeys;
import context.ScenarioContext;
import utililities.ConfigReader;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class ApiActions {
    protected ConfigReader configReader = new ConfigReader();
    protected static final Logger logger = LogManager.getLogger(ApiActions.class);
    protected ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();
    private Response response;
    private final UtilActions utilActions = new UtilActions();

    private RequestSpecification responseMethodWOBearer() {

        return given()
                .when();
    }

    private RequestSpecification responseMethod() {
        String bearer = scenarioContext.getData(ObjectKeys.BEARER).toString();
        return given().header("Authorization", "Bearer " + bearer)
                .when();
    }

    private RequestSpecification responseMethod(String bearer) {
        return given().header("Authorization", "Bearer " + bearer)
                .when();
    }

    public void getContactListHealthCheck() {
        try {
            String bearer = configReader.getProperty("bearer");
            baseURI = configReader.getProperty("baseURI");
            basePath = "/contacts";
            logger.info("Base URI and base path are set to: " + baseURI + basePath);

            response = responseMethod(bearer)
                    .get();

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                logger.info("GET request to contacts endpoint was successful with status code: " + response.getStatusCode());
            } else {
                logger.error("GET request to contacts endpoint failed. Status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            logger.error("An exception occurred while sending GET request to contacts endpoint: " + e.getMessage());
        }
    }

    public void getContactList() {
        baseURI = configReader.getProperty("baseURI");
        basePath = "/contacts";
        logger.info("Base URI and base path: " + baseURI + basePath);

        response = responseMethod()
                .get();
        response.then().log().all();

        List<String> firstNames = response.jsonPath().getList("firstName");
        boolean allHaveFirstName = firstNames.stream().allMatch(Objects::nonNull);

        scenarioContext.setData(ObjectKeys.FIRST_NAME_PRESENCE, allHaveFirstName);
        scenarioContext.setData(ObjectKeys.GET_STATUS_CODE, response.getStatusCode());
    }

    public int getContactListStatusCode() {
        int gStCode = (int) scenarioContext.getData(ObjectKeys.GET_STATUS_CODE);
        logger.debug(gStCode);
        return gStCode;
    }

    public void assertThatBodyContains(String expContent) {
        String body = ((Response) scenarioContext.getData(ObjectKeys.RESPONSE)).getBody().asString();
        assertTrue("Response body does not contain " + expContent, body.contains(expContent));
        logger.info("Is " + expContent + " present in the body: " + body.contains(expContent));
    }

    public void postRequestAddContactWithParameters(Map<String, String> params) {
        baseURI = configReader.getProperty("baseURI");
        basePath = "/contacts";
        logger.info("Current URI and base path: " + baseURI + basePath);
        response = responseMethod()
                .contentType(ContentType.JSON)
                .body(params)
                .log()
                .all()
                .post();
        response.then().log().all();

        String contactId = utilActions.getParamFromJson(response, "_id");
        scenarioContext.setData(ObjectKeys.NEW_CONTACT_ID, contactId);
        logger.info("Json parser got the value: " + contactId);
        scenarioContext.setData(ObjectKeys.POST_STATUS_CODE, response.getStatusCode());
        logger.debug("POST Status code:" + response.getStatusCode());
        scenarioContext.setData(ObjectKeys.RESPONSE, response.then().extract().response());
    }

    public void deleteContactById(String ContactId) {
        baseURI = configReader.getProperty("baseURI");
        basePath = "/contacts/" + ContactId;
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

    public void postRequestAddUserWithParameters(Map<String, String> params) {
        baseURI = configReader.getProperty("baseURI");
        basePath = "/users";
        logger.info("Current URI and base path: " + baseURI + basePath);
        response = responseMethodWOBearer()
                .contentType(ContentType.JSON)
                .body(params)
                .log()
                .all()
                .post();
        response.then().log().all();
        String token = utilActions.getParamFromJson(response, "token");
        scenarioContext.setData(ObjectKeys.BEARER, token);
        scenarioContext.setData(ObjectKeys.F_USER_NAME, utilActions.getParamFromJson(response, "user", "firstName"));
        scenarioContext.setData(ObjectKeys.L_USER_NAME, utilActions.getParamFromJson(response, "user", "lastName"));
        scenarioContext.setData(ObjectKeys.USER_EMAIL, utilActions.getParamFromJson(response, "user", "email"));
        logger.debug("POST Status code:" + response.getStatusCode());
        scenarioContext.setData(ObjectKeys.POST_STATUS_CODE, response.getStatusCode());
        scenarioContext.setData(ObjectKeys.RESPONSE, response.then().extract().response());

        logger.info("Json parser got the bearer value: " + utilActions.getParamFromJson(response, "token"));
    }

    public void deleteUserByBearer() {
        baseURI = configReader.getProperty("baseURI");
        basePath = "users/me";
        logger.debug("Will be deleted " + scenarioContext.getData(ObjectKeys.F_USER_NAME).toString()
                + " with token:" + scenarioContext.getData(ObjectKeys.BEARER).toString());
        response = responseMethod()
                .log()
                .all()
                .delete();
        response.then().log().all();
        scenarioContext.setData(ObjectKeys.DEL_USER_STATUS_CODE, response.getStatusCode());
    }
}
