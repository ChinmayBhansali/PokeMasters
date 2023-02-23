package model;

import model.pokemon.Growlithe;
import model.pokemon.Pikachu;
import model.pokemon.Weedle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setup() {
        player = new Player();
    }

    @Test
    void addPokeballsTest() {
        assertEquals(0, player.getPokeballs());
        player.addPokeballs(5);
        assertEquals(5, player.getPokeballs());
    }

    @Test
    void addPokemonTest() {
        assertEquals(0, player.getPokemon().size());
        player.addPokemon(new Pikachu(8));
        player.addPokemon(new Growlithe(9));
        assertEquals(2, player.getPokemon().size());
    }

    @Test
    void usePokeballTest() {
        player.addPokeballs(10);
        assertEquals(10, player.getPokeballs());
        player.usePokeball();
        assertEquals(9, player.getPokeballs());
    }

    @Test
    void releasePokemonTest() {
        player.addPokemon(new Weedle(3));
        assertEquals(1, player.getPokemon().size());
        player.releasePokemon(player.getPokemon().get(0));
        assertEquals(0, player.getPokemon().size());
    }

    @Test
    void addPokeDollarsTest() {
        assertEquals(0, player.getPokeDollars());
        player.addPokeDollars(300);
        assertEquals(300, player.getPokeDollars());
    }

    @Test
    void deductPokeDollarsTest() {
        player.addPokeDollars(500);
        assertEquals(500, player.getPokeDollars());
        player.deductPokeDollars(200);
        assertEquals(300, player.getPokeDollars());
    }
}