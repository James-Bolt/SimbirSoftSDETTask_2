import io.qameta.allure.Description;
import models.AbilitiesResponse;
import models.LimitResponse;
import models.WeightResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.stream.Collectors;

public class ApiTests extends BaseTest {

    @Test
    @Description("Проверка наличия способности 'run-away' у покемона rattata и её отсутствие у pidgeotto")
    public void pokemonAbilityTest() {

        AbilitiesResponse.Ability runAwayAbility = new AbilitiesResponse.Ability("run-away", "https://pokeapi.co/api/v2/ability/50/");

        List<AbilitiesResponse> abilityListRattata = getAbilitiesResponseList("rattata");
        List<AbilitiesResponse.Ability> abilitiesListRattata = abilityListRattata.stream()
                .map(AbilitiesResponse::getAbility)
                .collect(Collectors.toList());
        List<AbilitiesResponse> abilityListPidgeotto = getAbilitiesResponseList("pidgeotto");
        List<AbilitiesResponse.Ability> abilitiesListPidgeotto = abilityListPidgeotto.stream()
                .map(AbilitiesResponse::getAbility)
                .collect(Collectors.toList());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(abilitiesListRattata.contains(runAwayAbility), "Pokemon don't have that ability");
        softAssert.assertFalse(abilitiesListPidgeotto.contains(runAwayAbility), "Pokemon has this ability");
        softAssert.assertAll();
    }

    @Test
    @Description("Сравнение веса покемонов")
    public void pokemonWeightTest() {

        WeightResponse weightRattata = getWeightResponse("rattata");
        WeightResponse weightPidgeotto = getWeightResponse("pidgeotto");

        Assert.assertTrue(weightRattata.getWeight() < weightPidgeotto.getWeight(),
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
