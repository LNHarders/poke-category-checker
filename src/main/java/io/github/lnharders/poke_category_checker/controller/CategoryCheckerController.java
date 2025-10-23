package io.github.lnharders.poke_category_checker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.lnharders.poke_category_checker.logic.CategoryChecker;
import io.github.lnharders.poke_category_checker.model.CategoryType;
import reactor.core.publisher.Mono;


@RestController
public class CategoryCheckerController {

    private final CategoryChecker categoryChecker;

    public CategoryCheckerController(CategoryChecker categoryChecker) {
        this.categoryChecker = categoryChecker;
    }

    @GetMapping("/pokemon/filter")
    public Mono<List<String>> filterPokemon(
        @RequestParam CategoryType category1,
        @RequestParam String value1,
        @RequestParam CategoryType category2,
        @RequestParam String value2
    ) {
        return categoryChecker.getPokemonOfTwoCategories(category1, value1, category2, value2);
    }

    @GetMapping("/categories")
    public Mono<CategoryType[]> getCategories() {
        return Mono.just(CategoryType.values());
    }

    @GetMapping("/category/options")
    public Mono<List<String>> getCategoryOptions(@RequestParam CategoryType category) {
        return categoryChecker.getCategoryOptions(category);
    }    
}
