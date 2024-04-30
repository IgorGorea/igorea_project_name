package api.actions;

import api.dtos.requests.ContactRequest;
import com.github.javafaker.Faker;
import context.ObjectKeys;
import context.ScenarioContext;
import io.restassured.response.Response;
import utililities.JsonParser;

import java.time.Instant;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

public class UtilActions {
    private final Faker faker = new Faker();
    private final JsonParser jsonParser = new JsonParser();
    private final ScenarioContext scenarioContext = ScenarioContext.getScenarioInstance();

    public Map<String, String> newUserCredentials() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("firstName", faker.name().firstName());
        parameters.put("lastName", faker.name().lastName());
        parameters.put("email", faker.bothify("????##@gmail.com"));
        parameters.put("password", faker.number().digits(10));
        scenarioContext.setData(ObjectKeys.USER_PASS, parameters.get("password"));
        return parameters;
    }

    public void newContactCredentials(ContactRequest conObject) {
        conObject.setFirstName(faker.name().firstName());
        conObject.setLastName(faker.name().lastName());
        conObject.setBirthdate(Instant.ofEpochMilli(faker.date().birthday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate().toString());
        conObject.setEmail(faker.bothify("????##@gmail.com"));
        conObject.setPhone(faker.phoneNumber().subscriberNumber(10));
        conObject.setStreet1(faker.address().streetAddress());
        conObject.setStreet2(faker.address().secondaryAddress());
        conObject.setCity(faker.address().city());
        conObject.setStateProvince(faker.address().stateAbbr());
        conObject.setPostalCode(faker.address().zipCode());
        conObject.setCountry(faker.address().country());
    }

//    public Map<String, String> newContactCred1() {
//        Map<String, String> conParam = new HashMap<>();
//        conParam.put("firstName", faker.name().firstName());
//        conParam.put("lastName", faker.name().lastName());
//        conParam.put("birthdate", Instant.ofEpochMilli(faker.date().birthday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate().toString());
//        conParam.put("phone", faker.phoneNumber().subscriberNumber(10));
//        conParam.put("street1", faker.address().streetAddress());
//        conParam.put("street2", faker.address().secondaryAddress());
//        conParam.put("city", faker.address().city());
//        conParam.put("stateProvince", faker.address().stateAbbr());
//        conParam.put("postalCode", faker.address().zipCode());
//        conParam.put("country", faker.address().country());
//        return conParam;
//    }

//    public void getResponseCompleted2(Response response, ContactResponse conObject) {
//        conObject.setId(getParamFromJson(response, "_id"));
//        conObject.setFirstName(getParamFromJson(response, "firstName"));
//        conObject.setLastName(getParamFromJson(response, "lastName"));
//        conObject.setBirthdate(getParamFromJson(response, "birthdate"));
//        conObject.setEmail(getParamFromJson(response, "email"));
//        conObject.setPhone(getParamFromJson(response, "phone"));
//        conObject.setStreet1(getParamFromJson(response, "street1"));
//        conObject.setStreet2(getParamFromJson(response, "street2"));
//        conObject.setCity(getParamFromJson(response, "city"));
//        conObject.setStateProvince(getParamFromJson(response, "stateProvince"));
//        conObject.setPostalCode(getParamFromJson(response, "postalCode"));
//        conObject.setCountry(getParamFromJson(response, "country"));
//        conObject.setOwner(getParamFromJson(response, "owner"));
//        conObject.setVersion(Integer.parseInt(getParamFromJson(response, "__v")));
//    }


    public String getParamFromJson(Response response, String fieldName) {
        return jsonParser.extractValueFromResponseBody(response.getBody().asString(), fieldName);
    }

    public String getParamFromJson(Response response, String parentField, String fieldName) {
        return jsonParser.extractValueFromResponseBody(response.getBody().asString(), parentField, fieldName);
    }



}
