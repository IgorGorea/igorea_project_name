package api.actions;

import api.dtos.requests.UserRequest;
import api.dtos.responses.UserResponse;
import cfg.EndPoints;
import context.ObjectKeys;
import io.restassured.http.ContentType;

public class UserApiActions extends ApiActions {

    public void postRequestAddUserWithParameters() {
        baseURLSetting(EndPoints.USERS.getEndPoint());
        UserRequest reqBody = new UserRequest();
        utilActions.newUserCredentials(reqBody);

        response = responseMethodWOToken()
                .contentType(ContentType.JSON)
                .body(reqBody)
                .log().body()
                .post();
        response.then().log().body();

        UserResponse respBody = response.body().as(UserResponse.class);
        scenarioContext.setData(ObjectKeys.NEW_USER, respBody);
        scenarioContext.setData(ObjectKeys.USER_EMAIL, respBody.getUser().getEmail());
        logger.debug("POST Status code:{}", response.getStatusCode());
        scenarioContext.setData(ObjectKeys.POST_STATUS_CODE, response.getStatusCode());
        scenarioContext.setData(ObjectKeys.RESPONSE, response.then().extract().response());
        logger.info("Json parser got the token bearer value: {}", respBody.getToken());
    }

    public void deleteUserByToken() {
        baseURLSetting(EndPoints.USER_ME.getEndPoint());
        logger.debug("Will be deleted {} with token: {}", ((UserResponse) scenarioContext.getData((ObjectKeys.NEW_USER))).getUser().getFirstName(),
                ((UserResponse) scenarioContext.getData((ObjectKeys.NEW_USER))).getToken());

        response = responseMethod()
                .log()
                .body()
                .delete();
        response.then().log().body();

        scenarioContext.setData(ObjectKeys.DEL_USER_STATUS_CODE, response.getStatusCode());
    }
}
