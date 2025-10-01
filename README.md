# Poke Category Checker

A small Spring Boot project that queries the [PokéAPI](https://pokeapi.co/) and allows filtering Pokémon by two categories.

---

## Features

- Fetch Pokémon data from PokéAPI using [pokeapi-reactor](https://github.com/skaro-pokeapi/pokeapi-reactor)
- Filter Pokémon by **two categories** (type, ability, move, etc.)

---

## Requirements

- Java 17 or higher
- Maven 3.8+ (or use the included Maven Wrapper)

---

## Build & Run

Using Maven Wrapper (recommended):

```bash
# Build the project
./mvnw clean install

# Run the Spring Boot application
./mvnw spring-boot:run
```

Or using installed Maven:

    mvn clean install
    mvn spring-boot:run

The application runs on `http://localhost:8080/`.

---

## API Endpoints

| Endpoint | Description |
|----------|-------------|
| `GET /categories` | Returns available category types |
| `GET /category/options?category=TYPE` | Returns possible values for the given category |
| `GET /pokemon/filter?category1=TYPE&value1=X&category2=TYPE&value2=Y` | Returns a list of Pokémon matching both category-value filters |

### Example Query

    GET /pokemon/filter?category1=TYPE&value1=flying&category2=ABILITY&value2=static

Response:

    [
      "zapdos",
      "emolga"
    ]

---

## Web UI

This project includes a small demo page to test the application.

Access the UI at: `http://localhost:8080/`

---

## Dependencies

This project uses [pokeapi-reactor](https://github.com/skaro-pokeapi/pokeapi-reactor) to query the PokéAPI.

**Important:**  
`pokeapi-reactor` is not published to Maven Central, so it must be installed locally before building the project
