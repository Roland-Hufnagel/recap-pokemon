package org.example.recappokemon.dto;

import java.util.List;

public record Pokemon(
        String id,
        String name,
        Sprites sprites,
        int height,
        int weight,
        List<PokemonType> types
) {
}
