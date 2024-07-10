package api.actions;

import api.dtos.responses.UserResponse;
import cfg.EndPoints;
import context.ObjectKeys;
import context.ScenarioContext;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utililities.ConfigReader;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

public class ApiActions {
    protected ConfigReader configReader = new ConfigReader();
    protected static final Logger logger = LoggerFactory.getLogger(ApiActions.class);
    protected ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();
    protected Response response;
    protected final UtilActions utilActions = new UtilActions();


    protected RequestSpecification responseMethodWOToken() {
        return given()
                .when();
    }

    protected RequestSpecification responseMethod() {
        String token = ((UserResponse) scenarioContext.getData((ObjectKeys.NEW_USER))).getToken();
        return given().header("Authorization", "Bearer " + token)
                .when();
    }

    protected RequestSpecification responseMethod(String token) {
        return given().header("Authorization", "Bearer " + token)
                .when();
    }

    protected void baseURLSetting(String endPoint){
        baseURI = EndPoints.BASEURL.getEndPoint();
        basePath = endPoint;
        logger.info("Base URI and base path are set to: " + baseURI + basePath);
    }
    public void getContactListHealthCheck() {
        try {
            String token = configReader.getProperty("token.bearer");
            baseURLSetting(EndPoints.CONTACTS.getEndPoint());

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

    public void assertThatBodyContains(String expContent) {
        String body = ((Response) scenarioContext.getData(ObjectKeys.RESPONSE)).getBody().asString();
        assertTrue("Response body does not contain " + expContent, body.contains(expContent));
        logger.info("Is " + expContent + " present in the body: " + body.contains(expContent));
    }

}
