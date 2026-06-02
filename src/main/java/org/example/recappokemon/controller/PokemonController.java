package org.example.recappokemon.controller;

import org.example.recappokemon.dto.FavoriteDto;
import org.example.recappokemon.dto.PokeDto;
import org.example.recappokemon.model.Pokemon;
import org.example.recappokemon.service.PokemonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PokemonController {
    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    @GetMapping("/pokemon/{name}")
    PokeDto getPokemonByName(@PathVariable String name) {
        return service.findPokemonByName(name);
    }

    @PostMapping("/collection")
    Pokemon saveFavorite(@RequestBody FavoriteDto favDto) {
        return service.saveFavorite(favDto);
    }
}
