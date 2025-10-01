package io.github.lnharders.poke_category_checker.logic;

import java.util.Collections;
import java.util.List;
import io.github.lnharders.poke_category_checker.model.CategoryType;
import org.junit.jupiter.api.Test;

import io.github.lnharders.poke_category_checker.service.FakePokemonService;
import reactor.test.StepVerifier;

public class CategoryCheckerTest {
    
    @Test
    void getPokemonOfTwoCategories_returnsPokemonInBothCategories() {
        FakePokemonService service = new FakePokemonService();
        String value1 = "electric";
        String value2 = "static";

        service.setListForValue(value1, List.of("pikachu", "raichu", "pichu"));
        service.setListForValue(value2, List.of("pikachu"));

        List<String> expected = List.of("pikachu");

        CategoryChecker categoryChecker = new CategoryChecker(service);

        StepVerifier.create(categoryChecker.getPokemonOfTwoCategories(CategoryType.type, value1, CategoryType.ability, value2))
            .expectNext(expected)
            .verifyComplete();
    }

    @Test
    void getPokemonOfTwoCategories_withNoIntersections() {
        FakePokemonService service = new FakePokemonService();
        String value1 = "electric";
        String value2 = "fire";

        service.setListForValue(value1, List.of("pikachu", "raichu", "pichu"));
        service.setListForValue(value2, List.of("charmander", "charmeleon", "charizard"));

        List<String> expected = Collections.emptyList();

        CategoryChecker categoryChecker = new CategoryChecker(service);

        StepVerifier.create(categoryChecker.getPokemonOfTwoCategories(CategoryType.type, value1, CategoryType.type, value2))
            .expectNext(expected)
            .verifyComplete();
    }
}
