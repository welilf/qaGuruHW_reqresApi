package specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;

public class UserSpecs {

    public static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setBasePath("/api")
            .addHeader("x-api-key", "free_user_3G7QjXRJJbO8HHD5VkdypzMARxU")
            .addFilter(withCustomTemplates())
            .log(LogDetail.ALL)
            .setContentType(ContentType.JSON)
            .build();

    public static final ResponseSpecification responseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.BODY)
            .build();

    public static final ResponseSpecification responseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(LogDetail.BODY)
            .build();

    public static final ResponseSpecification responseSpec204 = new ResponseSpecBuilder()
            .expectStatusCode(204)
            .log(LogDetail.BODY)
            .build();

    public static final ResponseSpecification responseSpec400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.BODY)
            .build();
}