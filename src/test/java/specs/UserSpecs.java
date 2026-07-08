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

    public static final ResponseSpecification responseSpec (int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(LogDetail.BODY)
                .build();
    }
            }