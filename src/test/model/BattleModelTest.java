package model;

import model.pokemon.Onix;
import model.pokemon.Pidgey;
import model.pokemon.Pikachu;
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
    void activePokemonGainXPNoLevelUpTest() {
        Pokemon gainPokemonXP = testPlayer.getPokemon().get(0);
        assertEquals(2500, gainPokemonXP.getXP());
        boolean levelUp = testBM.activePokemonGainXP(testAttack);
        assertEquals(2540, gainPokemonXP.getXP());
        assertFalse(levelUp);
    }

    @Test
    void activePokemonGainXPCanLevelUp() {
        Pokemon gainPokemonXP = testPlayer.getPokemon().get(0);
        assertEquals(2500, gainPokemonXP.getXP());
        while (gainPokemonXP.getXP() < 3560) {
            testBM.activePokemonGainXP(testAttack);
        }
        boolean levelUp = testBM.activePokemonGainXP(testAttack);
        assertEquals(3620, gainPokemonXP.getXP());
        assertTrue(levelUp);
    }

    @Test
    void activePokemonLearnAttackTest() {
        testPlayer.getPokemon().get(0).gainXP(11900);
        assertEquals(2, testPlayer.getPokemon().get(0).getAttacks().size());
        assertFalse(testBM.activePokemonLearnAttack());
        testPlayer.getPokemon().get(0).levelUp();
        testPlayer.getPokemon().get(0).levelUp();
        testPlayer.getPokemon().get(0).levelUp();
        testPlayer.getPokemon().get(0).levelUp();
        testPlayer.getPokemon().get(0).levelUp();
        testPlayer.getPokemon().get(0).levelUp();
        testPlayer.getPokemon().get(0).levelUp();
        boolean learAttack = testBM.activePokemonLearnAttack();
        assertTrue(learAttack);
        assertEquals(3, testPlayer.getPokemon().get(0).getAttacks().size());
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
        int catchPokemon = testBM.usePokeball();
        assertEquals(4, testPlayer.getPokeballs());
        assertEquals(originalPokemon, testPlayer.getPokemon().size());
        assertEquals(0, catchPokemon);
        testBM.getWildPokemon().reduceHP(testBM.getWildPokemon().getHP() - (testBM.getWildPokemon().getHP() / 5));
        catchPokemon = testBM.usePokeball();
        assertEquals(3, testPlayer.getPokeballs());
        assertEquals(originalPokemon, testPlayer.getPokemon().size());
        assertEquals(0, catchPokemon);
    }

    @Test
    void usePokeballCatchPokemon() {
        int originalPokemon = testPlayer.getPokemon().size();
        assertEquals(5, testPlayer.getPokeballs());
        testBM.getWildPokemon().reduceHP(testBM.getWildPokemon().getHP() - (testBM.getWildPokemon().getHP() / 5) + 1);
        int catchPokemon = testBM.usePokeball();
        assertEquals(4, testPlayer.getPokeballs());
        assertEquals(originalPokemon + 1, testPlayer.getPokemon().size());
        assertEquals(1, catchPokemon);
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
        int catchPokemon = testBM.usePokeball();
        assertEquals(5, testPlayer.getPokeballs());
        assertEquals(originalPokemon, testPlayer.getPokemon().size());
        assertEquals(2, catchPokemon);
        testBM.getWildPokemon().reduceHP(1);
    }

    @Test
    void usePokeballsNoPokeballs() {
        testPlayer = new Player();
        testBM = new BattleModel(testPlayer);
        testPlayer.addPokemon(new BulbasaurStarter());
        assertEquals(0, testPlayer.getPokeballs());
        int use = testBM.usePokeball();
        assertEquals(0, testPlayer.getPokeballs());
        assertEquals(2, use);
    }

    @Test
    void getActivePokemonTest() {
        testPlayer.addPokemon(new Pikachu(2));
        Pokemon activePokemon = testBM.getActivePokemon();
        assertEquals("Bulbasaur", activePokemon.getName());
        activePokemon.reduceHP(activePokemon.getHP());
        activePokemon = testBM.getActivePokemon();
        assertEquals("Onix", activePokemon.getName());
        activePokemon.reduceHP(activePokemon.getHP());
        activePokemon = testBM.getActivePokemon();
        assertEquals("Pikachu", activePokemon.getName());
    }

    @Test
    void getActivePokemonAllPokemonFaint() {
        Pokemon activePokemon = testBM.getActivePokemon();
        assertEquals("Bulbasaur", activePokemon.getName());
        for (Pokemon p : testPlayer.getPokemon()) {
            p.reduceHP(p.getHP());
        }
        activePokemon = testBM.getActivePokemon();
        assertEquals("Bulbasaur", activePokemon.getName());
    }
}
