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

    public String getParamFromJson(Response response, String fieldName) {
        return jsonParser.extractValueFromResponseBody(response.getBody().asString(), fieldName);
    }

    public String getParamFromJson(Response response, String parentField, String fieldName) {
        return jsonParser.extractValueFromResponseBody(response.getBody().asString(), parentField, fieldName);
    }



}
