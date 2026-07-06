package tests;

import io.qameta.allure.*;
import models.request.AuthBodyModel;
import models.response.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.UserSpecs.*;

@Epic("Авторизация и списки")
@Feature("Аутентификация, регистрация и получение списков")
@Owner("Ponomarev Maksim")
public class AuthTests {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Регистрация")
    @DisplayName("Успешная регистрация пользователя")
    void successRegisterTest() {
        AuthBodyModel body = new AuthBodyModel();
        step("Подготовка учетных данных для регистрации", () -> {
            body.setEmail("eve.holt@reqres.in");
            body.setPassword("qaGuru");
        });

        RegisterResponseModel response = step("Отправка POST запроса на /register", () ->
                given(requestSpec)
                        .body(body)
                        .when()
                        .post("/register")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(RegisterResponseModel.class)
        );

        step("Проверка ID и токена в ответе", () -> {
            assertNotNull(response.getId());
            assertNotNull(response.getToken());
        });
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Авторизация")
    @DisplayName("Успешная авторизация (Логин)")
    void successLoginTest() {
        AuthBodyModel body = new AuthBodyModel();
        step("Подготовка учетных данных для входа", () -> {
            body.setEmail("eve.holt@reqres.in");
            body.setPassword("qaGuru9213");
        });

        LoginResponseModel response = step("Отправка POST запроса на /login", () ->
                given(requestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(LoginResponseModel.class)
        );

        step("Проверка наличия авторизационного токена", () -> assertNotNull(response.getToken()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Авторизация")
    @DisplayName("Негативный тест логина — без пароля")
    void negativeLoginMissingPasswordTest() {
        AuthBodyModel body = new AuthBodyModel();
        step("Указание только email (без пароля)", () -> body.setEmail("qa@guru"));

        AuthErrorResponseModel response = step("Отправка запроса на /login и ожидание ошибки 400", () ->
                given(requestSpec)
                        .body(body)
                        .when()
                        .post("/login")
                        .then()
                        .spec(responseSpec400)
                        .extract().as(AuthErrorResponseModel.class)
        );

        step("Проверка текста ошибки", () -> assertEquals("Missing password", response.getError()));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Получение списков")
    @DisplayName("Успешное получение списка пользователей")
    void successGetUsersListTest() {
        UserListResponseModel response = step("Запрос списка пользователей со страницы 2", () ->
                given(requestSpec)
                        .when()
                        .queryParam("page", 2)
                        .get("/users")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(UserListResponseModel.class)
        );

        step("Валидация данных полученного списка", () -> {
            assertEquals(2, response.getPage());
            assertNotNull(response.getData());
            assertNotNull(response.getData().get(0).getEmail());
        });
    }
}