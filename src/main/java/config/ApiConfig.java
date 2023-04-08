package config;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

public class ApiConfig {
    protected static MainConfig config = ConfigFactory.create(MainConfig.class, System.getenv());
    protected static final RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(config.baseUri())
            .addFilter(new AllureRestAssured())
            .build();
}
