package model;

import model.pokemon.Growlithe;
import model.pokemon.Pikachu;
import model.pokemon.Weedle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player testPlayer;

    @BeforeEach
    void setup() {
        testPlayer = new Player();
    }

    @Test
    void addPokeballsTest() {
        assertEquals(0, testPlayer.getPokeballs());
        testPlayer.addPokeballs(5);
        assertEquals(5, testPlayer.getPokeballs());
    }

    @Test
    void addPokemonTest() {
        assertEquals(0, testPlayer.getPokemon().size());
        testPlayer.addPokemon(new Pikachu(8));
        testPlayer.addPokemon(new Growlithe(9));
        assertEquals(2, testPlayer.getPokemon().size());
    }

    @Test
    void usePokeballTest() {
        testPlayer.addPokeballs(10);
        assertEquals(10, testPlayer.getPokeballs());
        testPlayer.usePokeball();
        assertEquals(9, testPlayer.getPokeballs());
    }

    @Test
    void usePokeballNoPokeballs() {
        testPlayer.usePokeball();
        assertEquals(0, testPlayer.getPokeballs());
    }

    @Test
    void releasePokemonTest() {
        testPlayer.addPokemon(new Weedle(3));
        assertEquals(1, testPlayer.getPokemon().size());
        testPlayer.releasePokemon(testPlayer.getPokemon().get(0));
        assertEquals(0, testPlayer.getPokemon().size());
    }

    @Test
    void addPokeDollarsTest() {
        assertEquals(0, testPlayer.getPokeDollars());
        testPlayer.addPokeDollars(300);
        assertEquals(300, testPlayer.getPokeDollars());
    }

    @Test
    void deductPokeDollarsTest() {
        testPlayer.addPokeDollars(500);
        assertEquals(500, testPlayer.getPokeDollars());
        testPlayer.deductPokeDollars(200);
        assertEquals(300, testPlayer.getPokeDollars());
    }
}