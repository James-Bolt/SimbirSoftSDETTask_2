import io.qameta.allure.Description;
import models.PokemonInfoResponse;
import models.PokemonInfoResponse.Ability.AbilityDetails;
import models.LimitResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static utils.TestObjectBuilder.getAbilityDetailsObject;
import static utils.TestObjectHelper.getAbilityDetailsList;

public class ApiTests extends BaseTest {

    @Test
    @Description("Проверка наличия способности 'run-away' у покемона rattata и её отсутствие у pidgeotto")
    public void pokemonAbilityTest() {

        AbilityDetails runAwayAbility = getAbilityDetailsObject("run-away", "https://pokeapi.co/api/v2/ability/50/");

        PokemonInfoResponse rattataInfoResponse = getAbilitiesResponseList("rattata");
        List<AbilityDetails> rattataAbilityDetailsList = getAbilityDetailsList(rattataInfoResponse);
        PokemonInfoResponse pidgeottoInfoResponse = getAbilitiesResponseList("pidgeotto");
        List<AbilityDetails> pidgeottoAbilityDetailsList = getAbilityDetailsList(pidgeottoInfoResponse);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(rattataAbilityDetailsList.contains(runAwayAbility), "Pokemon don't have that ability");
        softAssert.assertFalse(pidgeottoAbilityDetailsList.contains(runAwayAbility), "Pokemon has this ability");
        softAssert.assertAll();
    }

    @Test
    @Description("Сравнение веса покемонов")
    public void pokemonWeightTest() {

        PokemonInfoResponse rattataInfoResponse = getAbilitiesResponseList("rattata");
        PokemonInfoResponse pidgeottoInfoResponse = getAbilitiesResponseList("pidgeotto");

        Assert.assertTrue(rattataInfoResponse.getWeight() < pidgeottoInfoResponse.getWeight(),
                "The rattata weighs more than the pidgeotto.");
    }

    @Test
    @Description("Проверка количества элементов списка(limit)")
    public void limitSizeTest() {

        List<LimitResponse> limitList = getLimitResponseList(100);

        Assert.assertEquals(limitList.size(), 100, "Size does not match");
    }

    @Test
    @Description("Проверка наличия имён у покемонов в списке(limit)")
    public void limitNameTest() {

        List<LimitResponse> limitList = getLimitResponseList(50);

        SoftAssert softAssert = new SoftAssert();
        for (LimitResponse elem : limitList) {
            softAssert.assertNotNull(elem.getName(), "Pokemon has no name");
        }
    }
}
