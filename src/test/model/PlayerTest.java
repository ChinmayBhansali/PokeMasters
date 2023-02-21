package model;

import model.pokemon.Growlithe;
import model.pokemon.Pikachu;
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
        assertEquals(0, player.getPlayerPokemon().size());
        player.addPokemon(new Pikachu(8));
        player.addPokemon(new Growlithe(9));
        assertEquals(2, player.getPlayerPokemon().size());
    }

    @Test
    void usePokeballTest() {
        player.addPokeballs(10);
        assertEquals(10, player.getPokeballs());
        player.usePokeball();
        assertEquals(9, player.getPokeballs());
    }
}
