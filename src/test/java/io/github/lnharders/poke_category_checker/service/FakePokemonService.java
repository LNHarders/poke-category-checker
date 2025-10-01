package io.github.lnharders.poke_category_checker.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.lnharders.poke_category_checker.model.CategoryType;
import reactor.core.publisher.Mono;

public class FakePokemonService implements PokemonService{

    private final Map<String, List<String>> lists = new HashMap<>();

    @Override
    public Mono<List<String>> getPokemonOfMatchingCategory(CategoryType categoryType, String value) {
        List<String> result = lists.getOrDefault(value, Collections.emptyList());
        return Mono.just(result);
    }

    public void setListForValue(String value, List<String> list) {
        lists.put(value, list);
    }

    @Override
    public Mono<List<String>> getCategoryOptions(CategoryType categoryType) {
        return Mono.just(Collections.emptyList());
    }
}
