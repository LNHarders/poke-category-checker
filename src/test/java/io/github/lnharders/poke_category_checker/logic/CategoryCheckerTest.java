package io.github.lnharders.poke_category_checker.logic;

import java.util.Collections;
import java.util.List;
import io.github.lnharders.poke_category_checker.model.CategoryType;
import org.junit.jupiter.api.Test;

import io.github.lnharders.poke_category_checker.service.FakePokemonService;
import io.github.lnharders.poke_category_checker.service.category.CategoryServiceRegistry;
import reactor.test.StepVerifier;

public class CategoryCheckerTest {
    
    @Test
    void getPokemonOfTwoCategories_returnsPokemonInBothCategories() {
        FakePokemonService typeService = new FakePokemonService();
        FakePokemonService abilityService = new FakePokemonService();
        String value1 = "electric";
        String value2 = "static";

        typeService.setCategoryType(CategoryType.type);
        abilityService.setCategoryType(CategoryType.ability);

        typeService.setListForValue(value1, List.of("pikachu", "raichu", "pichu"));
        abilityService.setListForValue(value2, List.of("pikachu"));

        CategoryServiceRegistry services = new CategoryServiceRegistry(List.of(typeService, abilityService));

        List<String> expected = List.of("pikachu");

        CategoryChecker categoryChecker = new CategoryChecker(services);

        StepVerifier.create(categoryChecker.getPokemonOfTwoCategories(CategoryType.type, value1, CategoryType.ability, value2))
            .expectNext(expected)
            .verifyComplete();
    }

    @Test
    void getPokemonOfTwoCategories_withNoIntersections() {
        FakePokemonService typeService = new FakePokemonService();
        String value1 = "electric";
        String value2 = "fire";

        typeService.setCategoryType(CategoryType.type);

        typeService.setListForValue(value1, List.of("pikachu", "raichu", "pichu"));
        typeService.setListForValue(value2, List.of("charmander", "charmeleon", "charizard"));

        CategoryServiceRegistry services = new CategoryServiceRegistry(List.of(typeService));

        List<String> expected = Collections.emptyList();

        CategoryChecker categoryChecker = new CategoryChecker(services);

        StepVerifier.create(categoryChecker.getPokemonOfTwoCategories(CategoryType.type, value1, CategoryType.type, value2))
            .expectNext(expected)
            .verifyComplete();
    }
}
