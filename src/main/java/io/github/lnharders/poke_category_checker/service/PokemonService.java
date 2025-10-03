package io.github.lnharders.poke_category_checker.service;

import java.util.List;

import io.github.lnharders.poke_category_checker.model.CategoryType;
import reactor.core.publisher.Mono;

/**
 * Service for retrieving Pokémon of a specific category.
 */
public interface PokemonService {

    /**
     * Retrieves a list of Pokémon names that match a specific category and value.
     *
     * @param categoryType the category to filter by (e.g., TYPE, ABILITY)
     * @param value the value of the category (e.g., "electric" for TYPE)
     * @return list of matching Pokémon names
     * @throws UnsupportedOperationException if the category is not supported
     */
    Mono<List<String>> getPokemonOfMatchingCategory(CategoryType categoryType, String value);

    /**
     * Retrieves the available options for a given category (e.g., all types or abilities).
     *
     * @param categoryType the category to retrieve options for
     * @return list of available option names
     */
    Mono<List<String>> getCategoryOptions(CategoryType categoryType);
}
