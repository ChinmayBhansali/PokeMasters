package model;

import model.pokemon.Bulbasaur;
import model.pokemon.Pikachu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {
    private Pokemon p1;
    private Pokemon p2;

    @BeforeEach
    void setup() {
        p1 = new Pikachu(4);
    }

    @Test
    void reduceHPTest() {
        assertEquals(35 * p1.getLevel(), p1.getHP());
        p1.reduceHP(40);
        assertEquals(35 * p1.getLevel() - 40, p1.getHP());
    }

    @Test
    void restoreHealthTest() {
        assertEquals(35 * p1.getLevel(), p1.getHP());
        p1.reduceHP(40);
        assertEquals(35 * p1.getLevel() - 40, p1.getHP());
        p1.restoreHealth();
        assertEquals(35 * p1.getLevel(), p1.getHP());
    }

    @Test
    void isHealthCriticalTest() {
        assertFalse(p1.isHealthCritical());
        p1.reduceHP(28 * p1.getLevel());
        assertFalse(p1.isHealthCritical());
        p1.reduceHP(1);
        assertTrue(p1.isHealthCritical());
    }
}
