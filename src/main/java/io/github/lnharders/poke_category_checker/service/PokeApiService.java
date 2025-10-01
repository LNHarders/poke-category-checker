package io.github.lnharders.poke_category_checker.service;

import org.springframework.stereotype.Service;
import io.github.lnharders.poke_category_checker.model.CategoryType;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import reactor.core.publisher.Mono;
import skaro.pokeapi.client.PokeApiClient;
import skaro.pokeapi.resource.ability.Ability;
import skaro.pokeapi.resource.type.Type;
import skaro.pokeapi.resource.type.TypePokemon;

@Service
public class PokeApiService implements PokemonService {

    @Autowired
    private PokeApiClient pokeApiClient;

    @Override
    @Cacheable(value = "MatchingPokemon", key = "#categoryType + '_' + #value")
    public Mono<List<String>> getPokemonOfMatchingCategory(CategoryType categoryType, String value) {
        switch (categoryType) {
            case type:
                return getAllPokemonOfType(value);
            case ability:
                return getAllPokemonWithAbility(value);
            default:
                return Mono.just(Collections.emptyList());
        }
    }

    private Mono<List<String>> getAllPokemonOfType(String typeName) {
        return pokeApiClient.getResource(Type.class, typeName)
            .map(type -> {
                List<TypePokemon> typePokemon = type.getPokemon();
                return typePokemon.stream()
                    .map(pokemon -> pokemon.getPokemon().getName())
                    .toList();
            });
    }

    private Mono<List<String>> getAllPokemonWithAbility(String abilityName) {
        return pokeApiClient.getResource(Ability.class, abilityName)
            .map(ability -> {
                return ability.getPokemon()
                    .stream()
                    .map(pokemon -> pokemon.getPokemon().getName())
                    .toList();
            });
    }

    @Override
    @Cacheable(value = "CategoryOption", key = "#categoryType")
    public Mono<List<String>> getCategoryOptions(CategoryType categoryType) {
        switch (categoryType) {
            case type:
                return getTypeOptions();
            case ability:
                return getAbilityOptions();        
            default:
                return Mono.just(Collections.emptyList());
        }
    }

    private Mono<List<String>> getTypeOptions() {
        return pokeApiClient.getResource(Type.class)
            .map(types -> {
                return types.getResults()
                    .stream()
                    .map(type -> type.getName())
                    .toList();
            });
    }

    private Mono<List<String>> getAbilityOptions() {
        return pokeApiClient.getResource(Ability.class)
            .map(abilities -> {
                return abilities.getResults()
                    .stream()
                    .map(ability -> ability.getName())
                    .toList();
            });
    }
}
