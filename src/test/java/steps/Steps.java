package steps;

import config.ApiConfig;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import models.PokemonInfoResponse;
import models.LimitResponse;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Steps extends ApiConfig {

    @Step("Получение abilities покемона {PokemonName}")
    @Attachment
    public static PokemonInfoResponse getAbilitiesResponseList(String pokemonName) {
        return given(requestSpecification)
                .when()
                .pathParam("pokemonName", pokemonName)
                .get(POKEMON_PATH)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(PokemonInfoResponse.class);
    }

    @Step("Получение списка из {limit} покемонов")
    @Attachment
    public static List<LimitResponse> getLimitResponseList(int limit) {
        return given(requestSpecification)
                .when()
                .param("limit", limit)
                .get(BASE_PATH)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("results", LimitResponse.class);
    }
}
