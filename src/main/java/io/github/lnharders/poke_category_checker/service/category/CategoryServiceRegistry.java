package io.github.lnharders.poke_category_checker.service.category;

import org.springframework.stereotype.Component;

import io.github.lnharders.poke_category_checker.model.CategoryType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class CategoryServiceRegistry {

    private final Map<CategoryType, CategoryService> serviceMap = new EnumMap<>(CategoryType.class);

    public CategoryServiceRegistry(List<CategoryService> services) {
        for (CategoryService service : services) {
            serviceMap.put(service.getCategoryType(), service);
        }
    }

    public CategoryService getService(CategoryType categoryType) {
        CategoryService service = serviceMap.get(categoryType);
        if (service == null) {
            throw new IllegalArgumentException("Unsupported category: " + categoryType);
        }
        return service;
    }
}
