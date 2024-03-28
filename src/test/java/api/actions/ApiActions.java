package api.actions;

import api.utililities.ContextEnum;
import api.utililities.JsonParser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ui.actions.DriverActions;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class ApiActions extends DriverActions {
    String bearer = configReader.getProperty("bearer");
    private Response response;
    private RequestSpecification responseMethod(){
        return given().header("Authorization", "Bearer " + bearer)
                .when();
    }
    protected static final Logger logger = LogManager.getLogger();

    public void getContactListHealthCheck() {
        // Specify base URI and base path
        baseURI = configReader.getProperty("baseURI");
        basePath = "/contacts";
        logger.info("Base URI and base path are set to: " + baseURI + basePath);
        // Send GET request and verify response
        response = given().header("Authorization", "Bearer " + bearer)
                .when()
                .get();
                //TODO to move it in exceptions
//                .then()
//                .statusCode(200).extract().response();
    }

    public void getContactList() {
        baseURI = configReader.getProperty("baseURI");
        basePath = "/contacts";
        logger.info("Base URI and base path: " + baseURI + basePath);

        response = given().header("Authorization", "Bearer " + bearer)
                .when()
                .get();
        response.then().log().all();

        List<String> firstNames = response.jsonPath().getList("firstName");
        boolean allHaveFirstName = firstNames.stream().allMatch(Objects::nonNull);

        ContextEnum.FIRST_NAME_PRESENCE.saveBoolValue(allHaveFirstName);
        ContextEnum.GET_STATUS_CODE.saveIntValue(response.getStatusCode());
    }

    public int getContactListStatusCode() {
        int gStCode = ContextEnum.GET_STATUS_CODE.getIntValue();
        logger.debug(gStCode);
        return gStCode;
    }

    public String getResponseBody() {
        logger.debug("The body is: " + response.getBody().asString());
        return response.getBody().asString();
    }

    public void postRequestAddContactWithParameters(Map<String, String> params) {
        baseURI = configReader.getProperty("baseURI");
        basePath = "/contacts";
        logger.info("Current URI and base path: " + baseURI + basePath);
        response = given().header("Authorization", "Bearer " + bearer)
                .contentType(ContentType.JSON)
                .body(params)
                .log()
                .all()
                .post();
        response.then().log().all();
        String contactId = JsonParser.extractValueFromResponseBody(response.getBody().asString(), "_id");
        ContextEnum.NEW_CONTACT_ID.saveValue(contactId);
        logger.debug(response.getStatusCode());
        ContextEnum.POST_STATUS_CODE.saveIntValue(response.getStatusCode());

        logger.info("Json parser got the value: " + JsonParser.extractValueFromResponseBody(response.getBody().asString(), "_id"));
    }

    public void deleteContactById(String ContactId) {
        baseURI = configReader.getProperty("baseURI");
        basePath = "/contacts/" + ContactId;
        logger.debug(ContactId);
        response = given().header("Authorization", "Bearer " + bearer)
                .log()
                .all()
                .delete();
        response.then().log().all();
        ContextEnum.DEL_STATUS_CODE.saveIntValue(response.getStatusCode());
    }
}
