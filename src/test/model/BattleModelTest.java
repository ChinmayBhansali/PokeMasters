package model;

import model.pokemon.Onix;
import model.pokemon.Pidgey;
import model.pokemon.starters.BulbasaurStarter;
import model.pokemon.starters.CharmanderStarter;
import model.pokemon.starters.PikachuStarter;
import model.pokemon.starters.SquirtleStarter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleModelTest {
    private Player testPlayer;
    private Attack testAttack;
    private BattleModel testBM;

    @BeforeEach
    void setup() {
        testPlayer = new Player();
        testPlayer.addPokemon(new BulbasaurStarter());
        testPlayer.addPokemon(new Onix(7));
        testPlayer.addPokeballs(5);
        testAttack = testPlayer.getPokemon().get(0).getAttacks().get(0);
        testBM = new BattleModel(testPlayer);
    }

    @Test
    void playerAttackWildPokemonSurvivesTest() {
        int originalWildPokemonHP = testBM.getWildPokemon().getHP();
        boolean defeated = testBM.playerAttack(testAttack);
        assertEquals(originalWildPokemonHP - testAttack.getPower(), testBM.getWildPokemon().getHP());
        assertFalse(defeated);
    }

    @Test
    void playerAttackWildPokemonDefeatedTest() {
        int originalWildPokemonHP = testBM.getWildPokemon().getHP();
        testBM.getWildPokemon().reduceHP(originalWildPokemonHP - (testAttack.getPower() - 10));
        boolean defeated = testBM.playerAttack(testAttack);
        assertEquals(0, testBM.getWildPokemon().getHP());
        assertTrue(defeated);
        assertEquals(testBM.getWildPokemon().getLevel() * 10, testBM.getPlayer().getPokeDollars());
    }

    @Test
    void wildPokemonAttackActivePokemonSurvivesTest() {
        testBM.wildPokemonAttack();
        assertTrue(testBM.getActivePokemon().getHP() > 0);
    }

    @Test
    void wildPokemonAttackActivePokemonFaintsTest() {
        Pokemon originalActivePokemon = testBM.getActivePokemon();
        testBM.getActivePokemon().reduceHP(testBM.getActivePokemon().getHP() - 10);
        assertEquals(10, testBM.getActivePokemon().getHP());
        testBM.wildPokemonAttack();
        assertEquals(0, originalActivePokemon.getHP());
    }

    @Test
    void switchPokemonTest() {
        assertEquals("Bulbasaur", testBM.getActivePokemon().getName());
        testBM.switchPokemon(1);
        assertEquals("Onix", testBM.getActivePokemon().getName());
    }

    @Test
    void usePokeballCatchFail() {
        int originalPokemon = testPlayer.getPokemon().size();
        assertEquals(5, testPlayer.getPokeballs());
        boolean catchPokemon = testBM.usePokeball();
        assertEquals(4, testPlayer.getPokeballs());
        assertEquals(originalPokemon, testPlayer.getPokemon().size());
        assertFalse(catchPokemon);
        testBM.getWildPokemon().reduceHP(testBM.getWildPokemon().getHP() - (testBM.getWildPokemon().getHP() / 5));
        catchPokemon = testBM.usePokeball();
        assertEquals(3, testPlayer.getPokeballs());
        assertEquals(originalPokemon, testPlayer.getPokemon().size());
        assertFalse(catchPokemon);
    }

    @Test
    void usePokeballCatchPokemon() {
        int originalPokemon = testPlayer.getPokemon().size();
        assertEquals(5, testPlayer.getPokeballs());
        testBM.getWildPokemon().reduceHP(testBM.getWildPokemon().getHP() - (testBM.getWildPokemon().getHP() / 5) + 1);
        boolean catchPokemon = testBM.usePokeball();
        assertEquals(4, testPlayer.getPokeballs());
        assertEquals(originalPokemon + 1, testPlayer.getPokemon().size());
        assertTrue(catchPokemon);
    }

    @Test
    void usePokeballTeamFull() {
        testPlayer.addPokemon(new PikachuStarter());
        testPlayer.addPokemon(new SquirtleStarter());
        testPlayer.addPokemon(new CharmanderStarter());
        testPlayer.addPokemon(new Pidgey(8));
        int originalPokemon = testPlayer.getPokemon().size();
        assertEquals(5, testPlayer.getPokeballs());
        testBM.getWildPokemon().reduceHP(testBM.getWildPokemon().getHP() - (testBM.getWildPokemon().getHP() / 5) + 1);
        boolean catchPokemon = testBM.usePokeball();
        assertEquals(5, testPlayer.getPokeballs());
        assertEquals(originalPokemon, testPlayer.getPokemon().size());
        assertFalse(catchPokemon);
    }

    @Test
    void getActivePokemonTest() {
        Pokemon activePokemon = testBM.getActivePokemon();
        assertEquals("Bulbasaur", activePokemon.getName());
        activePokemon.reduceHP(activePokemon.getHP());
        activePokemon = testBM.getActivePokemon();
        assertEquals("Onix", activePokemon.getName());
    }
}
