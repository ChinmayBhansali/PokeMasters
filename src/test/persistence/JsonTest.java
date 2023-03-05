package persistence;

import model.Pokemon;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPokemon(String name, int level, int hp, int xp, Pokemon pokemon) {
        assertEquals(name, pokemon.getName());
        assertEquals(level, pokemon.getLevel());
        assertEquals(xp, pokemon.getXP());
        assertEquals(hp, pokemon.getHP());
    }
}
