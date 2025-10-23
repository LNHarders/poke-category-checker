package io.github.lnharders.poke_category_checker.service.category;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.lnharders.poke_category_checker.model.CategoryType;
import reactor.core.publisher.Mono;
import skaro.pokeapi.client.PokeApiClient;
import skaro.pokeapi.resource.ability.Ability;

@Service
public class AbilityService implements CategoryService {

    private final PokeApiClient pokeApiClient;

    public AbilityService(PokeApiClient pokeApiClient) {
        this.pokeApiClient = pokeApiClient;
    }

    @Override
    public CategoryType getCategoryType() {
        return CategoryType.ability;
    }

    @Override
    public Mono<List<String>> getCategoryOptions() {
        return pokeApiClient.getResource(Ability.class)
            .map(abilities -> {
                return abilities.getResults()
                    .stream()
                    .map(ability -> ability.getName())
                    .toList();
            });
    }

    @Override
    public Mono<List<String>> getPokemonOfValue(String value) {
        return pokeApiClient.getResource(Ability.class, value)
            .map(ability -> {
                return ability.getPokemon()
                    .stream()
                    .map(pokemon -> pokemon.getPokemon().getName())
                    .toList();
            });
    }    
}
