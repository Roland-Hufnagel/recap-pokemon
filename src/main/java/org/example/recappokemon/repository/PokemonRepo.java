package org.example.recappokemon.repository;

import org.example.recappokemon.model.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepo extends MongoRepository<Pokemon, String> {
}
