package api;

import io.restassured.response.ValidatableResponse;
import models.request.AuthBodyModel;
import specs.UserSpecs;

import static io.restassured.RestAssured.given;

public class AuthApiClient {

    public ValidatableResponse register(AuthBodyModel body, int statusCode) {
        return given(UserSpecs.requestSpec)
                .body(body)
                .when()
                .post("/register")
                .then()
                .spec(UserSpecs.responseSpec(statusCode));
    }

    public ValidatableResponse login(AuthBodyModel body, int statusCode) {
        return given(UserSpecs.requestSpec)
                .body(body)
                .when()
                .post("/login")
                .then()
                .spec(UserSpecs.responseSpec(statusCode));
    }

    public ValidatableResponse getUsersList(int page, int statusCode) {
        return given(UserSpecs.requestSpec)
                .queryParam("page", page)
                .when()
                .get("/users")
                .then()
                .spec(UserSpecs.responseSpec(statusCode));
    }
}