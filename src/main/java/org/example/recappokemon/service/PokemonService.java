package org.example.recappokemon.service;

import org.example.recappokemon.dto.FavoriteDto;
import org.example.recappokemon.dto.PokeDto;
import org.example.recappokemon.dto.PokemonApiDto;
import org.example.recappokemon.exceptions.NoSuchPokemonFoundException;
import org.example.recappokemon.model.Pokemon;
import org.example.recappokemon.repository.PokemonRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    private final RestClient restClient;
    private final IdService idService;
    private final PokemonRepo repo;

    public PokemonService(RestClient.Builder builder, IdService idService, PokemonRepo repo) {
        this.restClient = builder
                .baseUrl("https://pokeapi.co/api/v2/pokemon")
                .build();
        this.idService = idService;
        this.repo = repo;
    }

    public PokeDto findPokemonByName(String name) {
        PokemonApiDto p = restClient.get()
                .uri("/" + name)
                .retrieve()
                .body(PokemonApiDto.class);
        return convertPokemonToPokeDto(p);
    }

    public Pokemon saveFavorite(FavoriteDto favDto) {
        PokemonApiDto p = restClient.get()
                .uri("/" + favDto.pokemonName())
                .retrieve()
                .body(PokemonApiDto.class);
        if (p == null) {
            throw new NoSuchPokemonFoundException(
                    "not found: " + favDto.pokemonName());
        }
        PokeDto pdto = convertPokemonToPokeDto(p);
        return repo.save(new Pokemon(pdto.id(), pdto.pokemonId(), favDto.nickname(), pdto.pokemonName(), pdto.pictureUrl(), pdto.height(), pdto.weight(), pdto.types()));

    }


    // Helper:
    private PokeDto convertPokemonToPokeDto(PokemonApiDto p) {
        String id = idService.generateId();
        List<String> newTypes = new ArrayList<>();
        p.types().forEach(pt -> newTypes.add(pt.type().name()));
        return new PokeDto(id, p.id(), null, p.name(), p.sprites().other().officialArtwork().front_default(), p.height(), p.weight(), newTypes);
    }

}
