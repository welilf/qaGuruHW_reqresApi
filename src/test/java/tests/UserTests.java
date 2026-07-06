package tests;

import models.UserBodyModel;
import models.UserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.UserSpecs.requestSpec;
import static specs.UserSpecs.responseSpec201; // Исправленный импорт

@DisplayName("Операции с пользователями (/api/users)")
public class UserTests {

    @Test
    @DisplayName("Успешное создание пользователя")
    void successCreateUserTest() {
        // 1. Подготавливаем данные для запроса через модель
        UserBodyModel body = new UserBodyModel();
        body.setName("morpheus");
        body.setJob("leader");

        // 2. Отправляем POST запрос и десериализуем ответ
        UserResponseModel response = given(requestSpec)
                .body(body)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec201) // Исправленная переменная спецификации
                .extract().as(UserResponseModel.class);

        // 3. Проверяем поля в ответе
        assertEquals("morpheus", response.getName());
        assertEquals("leader", response.getJob());
        assertNotNull(response.getId());
        assertNotNull(response.getCreatedAt());
    }
}