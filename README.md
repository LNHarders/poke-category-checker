# Poke Category Checker

A small Spring Boot project that queries the [PokéAPI](https://pokeapi.co/) and allows filtering Pokémon by two categories.

---

## Features

- Fetch Pokémon data from PokéAPI using [pokeapi-reactor](https://github.com/SirSkaro/pokeapi-reactor)
- Filter Pokémon by **two categories** (currently only type, ability and generation are implemented)

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
| `GET /category/options?category=type` | Returns possible values for the given category |
| `GET /pokemon/filter?category1=type&value1=electric&category2=type&value2=flying` | Returns a list of Pokémon matching both category-value filters |

### Example Query

    GET /pokemon/filter?category1=type&value1=flying&category2=ability&value2=static

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

This project uses [pokeapi-reactor](https://github.com/SirSkaro/pokeapi-reactor) to query the PokéAPI.

**Important:**  
`pokeapi-reactor` is not published to Maven Central, so it must be installed locally before building the project

```bash
# Clone pokeapi-reactor locally
git clone https://github.com/SirSkaro/pokeapi-reactor.git
cd pokeapi-reactor

# Build and install locally to your Maven repository
./mvnw clean install
```