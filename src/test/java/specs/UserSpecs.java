package specs;

import config.ApiConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.aeonbits.owner.ConfigFactory;

import static helpers.CustomAllureListener.withCustomTemplates;

public class UserSpecs {

    private static final ApiConfig config = ConfigFactory.create(ApiConfig.class);

    public static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(config.baseUrl())
            .setBasePath(config.basePath())
            .addHeader("x-api-key", config.apiKey())
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