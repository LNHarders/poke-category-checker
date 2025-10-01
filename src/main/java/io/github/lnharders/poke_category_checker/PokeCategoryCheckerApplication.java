package io.github.lnharders.poke_category_checker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PokeCategoryCheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokeCategoryCheckerApplication.class, args);
	}

}
