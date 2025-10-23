package io.github.lnharders.poke_category_checker.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.lnharders.poke_category_checker.model.CategoryType;
import io.github.lnharders.poke_category_checker.service.category.CategoryService;
import io.github.lnharders.poke_category_checker.service.category.CategoryServiceRegistry;
import reactor.core.publisher.Mono;

@Service
public class CategoryChecker {

    private final CategoryServiceRegistry categoryServices;

    public CategoryChecker(CategoryServiceRegistry categoryServices) {
        this.categoryServices = categoryServices;
    }

    public Mono<List<String>> getPokemonOfTwoCategories(CategoryType categoryType1, String categoryValue1, CategoryType categoryType2, String categoryValue2) {
        CategoryService categoryOne = categoryServices.getService(categoryType1);
        CategoryService categoryTwo = categoryServices.getService(categoryType2);
        Mono<List<String>> categoryOneMatches = categoryOne.getPokemonOfValue(categoryValue1);
        Mono<List<String>> categoryTwoMatches = categoryTwo.getPokemonOfValue(categoryValue2);

        return Mono.zip(categoryOneMatches, categoryTwoMatches)
        .map(tuple -> {
            List<String> list1 = new ArrayList<>(tuple.getT1());
            List<String> list2 = tuple.getT2();
            list1.retainAll(new HashSet<String>(list2));
            return list1;
        });
    }

    public Mono<List<String>> getCategoryOptions(CategoryType categoryType) {
        return categoryServices.getService(categoryType).getCategoryOptions();
    }
}
