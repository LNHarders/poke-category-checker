package io.github.lnharders.poke_category_checker.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.lnharders.poke_category_checker.model.CategoryType;
import io.github.lnharders.poke_category_checker.service.PokemonService;
import reactor.core.publisher.Mono;

@Service
public class CategoryChecker {

    private final PokemonService pokemonService;

    public CategoryChecker(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    public Mono<List<String>> getPokemonOfTwoCategories(CategoryType categoryType1, String categoryValue1, CategoryType categoryType2, String categoryValue2) {
        Mono<List<String>> categoryOneMatches = pokemonService.getPokemonOfMatchingCategory(categoryType1, categoryValue1);
        Mono<List<String>> categoryTwoMatches = pokemonService.getPokemonOfMatchingCategory(categoryType2, categoryValue2);

        return Mono.zip(categoryOneMatches, categoryTwoMatches)
        .map(tuple -> {
            List<String> list1 = new ArrayList<>(tuple.getT1());
            List<String> list2 = tuple.getT2();
            list1.retainAll(new HashSet<String>(list2));
            return list1;
        });
    }
}
