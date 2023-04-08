package steps;

import config.ApiConfig;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import models.AbilitiesResponse;
import models.LimitResponse;
import models.WeightResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Steps extends ApiConfig {

    @Step("Получение abilities покемона {PokemonName}")
    @Attachment
    public static List<AbilitiesResponse> getAbilitiesResponseList(String PokemonName) {
        return given(requestSpecification)
                .when()
                .get("pokemon/" + PokemonName)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .jsonPath().getList("abilities", AbilitiesResponse.class);
    }

    @Step("Получение веса покемона {PokemonName}")
    @Attachment
    public static WeightResponse getWeightResponse(String PokemonName) {
        return given(requestSpecification)
                .when()
                .get("pokemon/" + PokemonName)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(WeightResponse.class);
    }

    @Step("Получение списка из {count} покемонов")
    @Attachment
    public static List<LimitResponse> getLimitResponseList(int count) {
        return given(requestSpecification)
                .when()
                .get("pokemon?limit=" + count)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("results", LimitResponse.class);
    }
}
