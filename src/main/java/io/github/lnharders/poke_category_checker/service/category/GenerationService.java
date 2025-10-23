package io.github.lnharders.poke_category_checker.service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.lnharders.poke_category_checker.model.CategoryType;
import reactor.core.publisher.Mono;
import skaro.pokeapi.client.PokeApiClient;
import skaro.pokeapi.resource.generation.Generation;

@Service
public class GenerationService implements CategoryService {

    private final PokeApiClient pokeApiClient;

    public GenerationService(PokeApiClient pokeApiClient) {
        this.pokeApiClient = pokeApiClient;
    }

    @Override
    public CategoryType getCategoryType() {
        return CategoryType.generation;
    }

    @Override
    public Mono<List<String>> getCategoryOptions() {
        return pokeApiClient.getResource(Generation.class)
            .map(generation -> {
                return generation.getResults()
                    .stream()
                    .map(ability -> ability.getName())
                    .toList();
            });
    }

    @Override
    public Mono<List<String>> getPokemonOfValue(String value) {
        return pokeApiClient.getResource(Generation.class, value)
            .map(generation -> {
                return generation.getPokemonSpecies()
                    .stream()
                    .map(pokemon -> pokemon.getName())
                    .toList();
            });
    }    
}
