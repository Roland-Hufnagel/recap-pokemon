package org.example.recappokemon.service;

import org.example.recappokemon.dto.PokeDto;
import org.example.recappokemon.dto.Pokemon;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    private final RestClient restClient;
    private final IdService idService;

    public PokemonService(RestClient.Builder builder, IdService idService) {
        this.restClient = builder
                .baseUrl("https://pokeapi.co/api/v2/pokemon")
                .build();
        this.idService = idService;
    }

    public PokeDto findPokemonByName(String name) {
        System.out.println(name);
        Pokemon p = restClient.get()
                .uri("/" + name)
                .retrieve()
                .body(Pokemon.class);
        System.out.println(p);
        return convertPokemonToPokeDto(p);
    }

    // Helper:
    private PokeDto convertPokemonToPokeDto(Pokemon p) {
        String id = idService.generateId();
        List<String> newTypes = new ArrayList<>();
        p.types().forEach(pt -> newTypes.add(pt.type().name()));
        return new PokeDto(id, p.id(), null, p.name(), p.sprites().other().officialArtwork().front_default(), p.height(), p.weight(), newTypes);
    }
}
