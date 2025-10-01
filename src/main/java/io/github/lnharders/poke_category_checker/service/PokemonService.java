package io.github.lnharders.poke_category_checker.service;

import java.util.List;

import io.github.lnharders.poke_category_checker.model.CategoryType;
import reactor.core.publisher.Mono;

public interface PokemonService {
    Mono<List<String>> getPokemonOfMatchingCategory(CategoryType categoryType, String value);

    Mono<List<String>> getCategoryOptions(CategoryType categoryType);
}
