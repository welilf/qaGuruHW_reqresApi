package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;

public class UserSpecs {

    // Базовая спецификация для всех ЗАПРОСОВ
    public static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setBasePath("/api")
            .addHeader("x-api-key", "free_user_3G7QjXRJJbO8HHD5VkdypzMARxU")
            .addFilter(withCustomTemplates()) // Твой кастомный Allure логгер запросов/ответов
            .log(LogDetail.ALL)               // Дублировать логи в консоль Идеи
            .setContentType(ContentType.JSON)
            .build();

    // Спецификация для ответов со статусом 200 OK (Логин, Регистрация, Список, Обновление)
    public static final ResponseSpecification responseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.BODY)
            .build();

    // Спецификация для ответов со статусом 201 Created (Создание пользователя)
    public static final ResponseSpecification responseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.BODY)
            .build();

    // Спецификация для ответов со статусом 204 No Content (Удаление пользователя)
    public static final ResponseSpecification responseSpec204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.BODY)
            .build();

    // Спецификация для ответов со статусом 400 Bad Request (Негативные тесты авторизации)
    public static final ResponseSpecification responseSpec400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.BODY)
            .build();
}