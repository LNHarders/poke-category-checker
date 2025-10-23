package io.github.lnharders.poke_category_checker.service.category;

import reactor.core.publisher.Mono;

import java.util.List;

import io.github.lnharders.poke_category_checker.model.CategoryType;

public interface CategoryService {
    CategoryType getCategoryType();

    Mono<List<String>> getCategoryOptions();
    Mono<List<String>> getPokemonOfValue(String value);
}