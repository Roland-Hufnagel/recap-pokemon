package org.example.recappokemon.dto;

import java.util.List;

public record PokeDto(
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
