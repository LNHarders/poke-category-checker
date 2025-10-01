package io.github.lnharders.poke_category_checker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import skaro.pokeapi.PokeApiReactorNonCachingConfiguration;

import java.time.Duration;
import io.netty.resolver.DefaultAddressResolverGroup;

@Configuration
@Import(PokeApiReactorNonCachingConfiguration.class)
public class PokeApiConfig {

    @Bean
    public ConnectionProvider connectionProvider() {
        return ConnectionProvider.builder("Auto refresh & no connection limit")
                .maxIdleTime(Duration.ofSeconds(10))
                .maxConnections(500)
                .pendingAcquireMaxCount(-1)
                .build();
    }

    @Bean
    public HttpClient httpClient(ConnectionProvider connectionProvider) {
        return HttpClient.create(connectionProvider)
                .compress(true)
                .resolver(DefaultAddressResolverGroup.INSTANCE);
    }
}
