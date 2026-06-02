package org.example.recappokemon.exceptions;

public class NoSuchPokemonFoundException extends RuntimeException {
    public NoSuchPokemonFoundException(String message) {
        super(message);
    }
}
