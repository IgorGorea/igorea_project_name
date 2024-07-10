package api.actions;

import api.dtos.requests.ContactRequest;
import api.dtos.responses.ContactResponse;
import cfg.EndPoints;
import context.ObjectKeys;
import io.restassured.http.ContentType;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class ContactApiActions extends ApiActions {
    public void getContactList() {
        baseURLSetting(EndPoints.CONTACTS.getEndPoint());

        response = responseMethod()
                .get();
        response.then().log().body();

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

    public void postRequestAddContactWithParameters(Map<String, String> params) {
        baseURLSetting(EndPoints.CONTACTS.getEndPoint());
        ContactRequest reqBody = new ContactRequest();
        utilActions.newContactCredentials(reqBody, params);
        
        response = responseMethod()
                .contentType(ContentType.JSON)
                .body(reqBody)
                .log()
                .body()
                .post();
        response.then().log().body();
        
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
        baseURLSetting(EndPoints.CONTACTS.getEndPoint());
        ContactRequest reqBody = new ContactRequest();
        utilActions.newContactCredentials(reqBody);
        response = responseMethod()
                .contentType(ContentType.JSON)
                .body(reqBody)
                .log()
                .body()
                .post();
        response.then().log().body();
        
        String contactId = utilActions.getParamFromJson(response, "_id");
        ContactResponse respBody = response.body().as(ContactResponse.class);
        logger.debug("Response body: " + respBody.toString());
        scenarioContext.setData(ObjectKeys.NEW_CONTACT_ID, contactId);
        logger.info("Json parser got the value: " + contactId);
        scenarioContext.setData(ObjectKeys.POST_STATUS_CODE, response.getStatusCode());
        logger.debug("POST Status code:" + response.getStatusCode());
        scenarioContext.setData(ObjectKeys.RESPONSE, response.then().extract().response());
    }

    public void deleteContactById(String ContactId) {
        baseURI = configReader.getProperty("baseURL");
        basePath = configReader.getProperty("baseContactsPath") + "/" + ContactId;
        logger.debug("Contact ID to be deleted:" + ContactId);
        response = responseMethod()
                .log()
                .body()
                .delete();
        response.then().log().body();

        logger.debug("DelStatCode:" + response.getStatusCode());
        scenarioContext.setData(ObjectKeys.DEL_CONTACT_STATUS_CODE, response.getStatusCode());
        scenarioContext.setData(ObjectKeys.RESPONSE, response.then().extract().response());
    }
}
