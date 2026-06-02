package org.example.recappokemon.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public record Pokemon(
        @Id
        String id,
        String pokemonId,
        String nickname,
        String pokemonName,
        String pictureUrl,
        int height,
        int weight,
        List<String> types
) {
}
