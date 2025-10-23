package io.github.lnharders.poke_category_checker.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.lnharders.poke_category_checker.model.CategoryType;
import io.github.lnharders.poke_category_checker.service.category.CategoryService;
import reactor.core.publisher.Mono;

public class FakePokemonService implements CategoryService {

    private final Map<String, List<String>> lists = new HashMap<>();
    private CategoryType categoryType;

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public void setListForValue(String value, List<String> list) {
        lists.put(value, list);
    }

    @Override
    public CategoryType getCategoryType() {
        return this.categoryType;
    }

    @Override
    public Mono<List<String>> getCategoryOptions() {
        return Mono.just(Collections.emptyList());
    }

    @Override
    public Mono<List<String>> getPokemonOfValue(String value) {
        List<String> result = lists.getOrDefault(value, Collections.emptyList());
        return Mono.just(result);
    }
}
