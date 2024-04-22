package api.actions;

import utililities.JsonParser;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import context.ObjectKeys;
import context.ScenarioContext;

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

    public Map<String, String> newContactCredentials() {
        Map<String, String> conParam = new HashMap<>();
        conParam.put("firstName", faker.name().firstName());
        conParam.put("lastName", faker.name().lastName());
        conParam.put("birthdate", Instant.ofEpochMilli(faker.date().birthday().getTime()).atZone(ZoneId.systemDefault()).toLocalDate().toString());
        conParam.put("phone", faker.phoneNumber().subscriberNumber(10));
        conParam.put("street1", faker.address().streetAddress());
        conParam.put("street2", faker.address().secondaryAddress());
        conParam.put("city", faker.address().city());
        conParam.put("stateProvince", faker.address().stateAbbr());
        conParam.put("postalCode", faker.address().zipCode());
        conParam.put("country", faker.address().country());
        scenarioContext.setData(ObjectKeys.USER_PASS, conParam.get("password"));
        return conParam;
    }

    public String getParamFromJson(Response response, String fieldName) {
        return jsonParser.extractValueFromResponseBody(response.getBody().asString(), fieldName);
    }

    public String getParamFromJson(Response response, String parentField, String fieldName) {
        return jsonParser.extractValueFromResponseBody(response.getBody().asString(), parentField, fieldName);
    }

}
