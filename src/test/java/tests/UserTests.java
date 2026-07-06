package tests;

import io.qameta.allure.*;
import models.request.UserBodyModel;
import models.response.UserResponseModel;
import models.response.UserPatchResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.UserSpecs.*;

@Epic("Управление пользователями")
@Feature("Операции с пользователями (/api/users)")
@Owner("Ponomarev Maksim")
public class UserTests {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Создание пользователей")
    @DisplayName("Успешное создание пользователя")
    void successCreateUserTest() {
        UserBodyModel body = new UserBodyModel();
        step("Подготовка тестовых данных", () -> {
            body.setName("Maksim");
            body.setJob("qaGuru");
        });

        UserResponseModel response = step("Отправка POST запроса на создание пользователя", () ->
                given(requestSpec)
                        .body(body)
                        .when()
                        .post("/users")
                        .then()
                        .spec(responseSpec201)
                        .extract().as(UserResponseModel.class)
        );

        step("Проверка полей в ответе от сервера", () -> {
            assertEquals("Maksim", response.getName());
            assertEquals("qaGuru", response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Обновление пользователей")
    @DisplayName("Успешное частичное обновление пользователя")
    void successPatchUserTest() {
        UserBodyModel body = new UserBodyModel();
        step("Подготовка данных для обновления", () -> body.setJob("sber"));

        UserPatchResponseModel response = step("Отправка PATCH запроса на обновление", () ->
                given(requestSpec)
                        .body(body)
                        .when()
                        .patch("/users/2")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(UserPatchResponseModel.class)
        );

        step("Проверка обновленных данных", () -> {
            assertEquals("sber", response.getJob());
            assertNotNull(response.getUpdatedAt());
        });
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Удаление пользователей")
    @DisplayName("Успешное удаление пользователя")
    void successDeleteUserTest() {
        step("Отправка DELETE запроса и проверка статус-кода 204", () ->
                given(requestSpec)
                        .when()
                        .delete("/users/2")
                        .then()
                        .spec(responseSpec204)
        );
    }
}