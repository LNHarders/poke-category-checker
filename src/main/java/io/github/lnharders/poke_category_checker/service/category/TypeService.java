package io.github.lnharders.poke_category_checker.service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.lnharders.poke_category_checker.model.CategoryType;
import reactor.core.publisher.Mono;
import skaro.pokeapi.client.PokeApiClient;
import skaro.pokeapi.resource.type.Type;
import skaro.pokeapi.resource.type.TypePokemon;

@Service
public class TypeService implements CategoryService {

    private final PokeApiClient pokeApiClient;

    public TypeService(PokeApiClient pokeApiClient) {
        this.pokeApiClient = pokeApiClient;
    }

    @Override
    public CategoryType getCategoryType() {
        return CategoryType.type;
    }

    @Override
    public Mono<List<String>> getCategoryOptions() {
        return pokeApiClient.getResource(Type.class)
            .map(types -> {
                return types.getResults()
                    .stream()
                    .map(type -> type.getName())
                    .toList();
            });
    }

    @Override
    public Mono<List<String>> getPokemonOfValue(String value) {
        return pokeApiClient.getResource(Type.class, value)
            .map(type -> {
                List<TypePokemon> typePokemon = type.getPokemon();
                return typePokemon.stream()
                    .map(pokemon -> pokemon.getPokemon().getName())
                    .toList();
            });
    }    
}
