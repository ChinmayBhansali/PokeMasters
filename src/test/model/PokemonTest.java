package model;

import model.pokemon.*;
import model.pokemon.starters.BulbasaurStarter;
import model.pokemon.starters.CharmanderStarter;
import model.pokemon.starters.PikachuStarter;
import model.pokemon.starters.SquirtleStarter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {
    private Pokemon p1;
    private Pokemon p2;
    private Pokemon p3;
    private Pokemon p4;
    private Pokemon p5;
    private Pokemon p6;
    private Pokemon p7;
    private Pokemon p8;
    private Pokemon p9;
    private Pokemon p10;
    private Pokemon p11;
    private Pokemon p12;
    private Pokemon p13;
    private Pokemon p14;
    private Pokemon p15;
    private Pokemon p16;

    private Pokemon sp1;
    private Pokemon sp2;
    private Pokemon sp3;
    private Pokemon sp4;

    private ArrayList<Pokemon> pokemon;
    private ArrayList<Pokemon> starterPokemon;
    private ArrayList<String> pokemonNames;


    @BeforeEach
    void setup() {
        p1 = new Bulbasaur(18);
        p2 = new Caterpie(9);
        p3 = new Charmander(17);
        p4 = new Diglett(16);
        p5 = new Growlithe(26);
        p6 = new Meowth(12);
        p7 = new Mewtwo(20);
        p8 = new Onix(8);
        p9 = new Pidgey(21);
        p10 = new Pikachu(20);
        p11 = new Psyduck(10);
        p12 = new Rattata(16);
        p13 = new Slowpoke(18);
        p14 = new Spearow(15);
        p15 = new Squirtle(12);
        p16 = new Weedle(9);

        sp1 = new BulbasaurStarter();
        sp2 = new CharmanderStarter();
        sp3 = new SquirtleStarter();
        sp4 = new PikachuStarter();

        pokemon = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16));
        starterPokemon = new ArrayList<>(Arrays.asList(sp1, sp2, sp3, sp4));

        pokemonNames = new ArrayList<>(Arrays.asList("Bulbasaur", "Caterpie", "Charmander", "Diglett", "Growlithe",
                "Meowth", "Mewtwo", "Onix", "Pidgey", "Pikachu", "Psyduck", "Rattata", "Slowpoke", "Spearow",
                "Squirtle", "Weedle"));
    }

    @Test
    void constructorTest() {
        int i = 0;
        for (Pokemon p : pokemon) {
            assertEquals(pokemonNames.get(i), p.getName());
            i++;
        }
        for (Pokemon p : starterPokemon) {
            assertEquals(5, p.getLevel());
        }
        assertEquals("001", p1.getPokedexNumber());
        assertEquals(4, p1.getAttacks().size());
    }

    @Test
    void reduceHPTest() {
        assertEquals(p1.getMaxHP(), p1.getHP());
        p1.reduceHP(40);
        assertEquals(p1.getMaxHP() - 40, p1.getHP());
    }

    @Test
    void gainXPTest() {
        assertEquals(32400, p1.getXP());
        p1.gainXP(150);
        assertEquals(32550, p1.getXP());
    }

    @Test
    void noLevelUpTest() {
        boolean levelUp = p1.levelUp();
        assertFalse(levelUp);
        p1.gainXP(299);
        levelUp = p1.levelUp();
        assertFalse(levelUp);
    }

    @Test
    void levelUpTest() {
        boolean levelUp = p1.levelUp();
        assertFalse(levelUp);
        p1.gainXP(3700);
        levelUp = p1.levelUp();
        assertTrue(levelUp);
    }

    @Test
    void isHealthCriticalTest() {
        assertFalse(p1.isHealthCritical());
        p1.reduceHP(36 * p1.getLevel());
        assertFalse(p1.isHealthCritical());
        p1.reduceHP(1);
        assertTrue(p1.isHealthCritical());
    }

    @Test
    void getAttacksTest() {
        assertEquals("Tackle, Vine Whip, Razor Leaf, Seed Bomb", p1.getAttackNames());
    }
}