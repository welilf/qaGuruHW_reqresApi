package api;

import io.restassured.response.ValidatableResponse;
import models.request.UserBodyModel;
import specs.UserSpecs;

import static io.restassured.RestAssured.given;

public class UsersApiClient {

    public ValidatableResponse createUser(UserBodyModel body, int statusCode) {
        return given(UserSpecs.requestSpec)
                .body(body)
                .when()
                .post("/users")
                .then()
                .spec(UserSpecs.responseSpec(statusCode));
    }

    public ValidatableResponse patchUser(String userId, UserBodyModel body, int statusCode) {
        return given(UserSpecs.requestSpec)
                .body(body)
                .when()
                .patch("/users/" + userId)
                .then()
                .spec(UserSpecs.responseSpec(statusCode));
    }

    public ValidatableResponse deleteUser(String userId, int statusCode) {
        return given(UserSpecs.requestSpec)
                .when()
                .delete("/users/" + userId)
                .then()
                .spec(UserSpecs.responseSpec(statusCode));
    }
}