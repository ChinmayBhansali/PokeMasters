package model;

import model.pokemon.Bulbasaur;
import model.pokemon.Pikachu;
import model.pokemon.starters.BulbasaurStarter;
import model.pokemon.starters.CharmanderStarter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static model.GameModel.STARTING_POKEBALLS;
import static model.GameModel.STARTING_POKEDOLLARS;
import static org.junit.jupiter.api.Assertions.*;

public class GameModelTest {
    private Player testPlayer;
    private GameModel testGameModel;

    @BeforeEach
    void setup() {
        testPlayer = new Player();
        testGameModel = new GameModel(testPlayer, "./data/testGeneralPlayer.json");
    }

    @Test
    void loadGameTest() throws IOException {
        assertEquals(0, testPlayer.getPokeballs());
        assertEquals(0, testPlayer.getPokeDollars());
        assertEquals(0, testPlayer.getPokemon().size());
        testPlayer = testGameModel.loadGame();
        assertEquals(5, testPlayer.getPokeballs());
        assertEquals(500, testPlayer.getPokeDollars());
        assertEquals(2, testPlayer.getPokemon().size());
    }

    @Test
    void assignStarterMysteriousPokemon() {
        assertEquals(0, testPlayer.getPokemon().size());
        String mysteriousPokemon = testGameModel.assignStarter(294);
        assertEquals(1, testPlayer.getPokemon().size());
        assertEquals("Mewtwo", mysteriousPokemon);
    }

    @Test
    void assignStarterNormalTest() {
        assertEquals(0, testPlayer.getPokemon().size());
        ArrayList<String> starterPokemonNames = new ArrayList<>(Arrays.asList("Bulbasaur", "Charmander", "Squirtle"));
        for (int i = 0; i < 3; i++) {
            assertEquals(starterPokemonNames.get(i), testGameModel.assignStarter(i));
        }
        assertEquals(3, testPlayer.getPokemon().size());
    }

    @Test
    void assignStarterPokemonErrorTest() {
        assertEquals(0, testPlayer.getPokemon().size());
        String errorPokemon = testGameModel.assignStarter(3);
        assertEquals(1, testPlayer.getPokemon().size());
        assertEquals("Pikachu", errorPokemon);
    }

    @Test
    void readyPlayerTest() {
        assertEquals(0, testPlayer.getPokemon().size());
        assertEquals(0, testPlayer.getPokeDollars());
        testGameModel.readyPlayer();
        assertEquals(STARTING_POKEBALLS, testPlayer.getPokeballs());
        assertEquals(STARTING_POKEDOLLARS, testPlayer.getPokeDollars());
    }

    @Test
    void encounterCannotBattle() {
        assertFalse(testGameModel.encounter());
        testPlayer.addPokemon(new Bulbasaur(1));
        testPlayer.getPokemon().get(0).reduceHP(45);
        assertFalse(testGameModel.encounter());
    }

    @Test
    void encounterTest() {
        assertFalse(testGameModel.encounter());
        testPlayer.addPokemon(new Bulbasaur(1));
        assertTrue(testGameModel.encounter());
    }

    @Test
    void releasePokemonTest() {
        testPlayer.addPokemon(new BulbasaurStarter());
        assertEquals(1, testPlayer.getPokemon().size());
        testGameModel.releasePokemon(testPlayer.getPokemon().get(0));
        assertEquals(0, testPlayer.getPokemon().size());
    }

    @Test
    void restorePokemonHealthTest() {
        testPlayer.addPokemon(new BulbasaurStarter());
        testPlayer.addPokemon(new CharmanderStarter());
        testPlayer.getPokemon().get(0).reduceHP(100);
        testPlayer.getPokemon().get(1).reduceHP(195);
        assertEquals(125, testPlayer.getPokemon().get(0).getHP());
        assertEquals(0, testPlayer.getPokemon().get(1).getHP());
        testGameModel.restorePokemonHealth();
        assertEquals(225, testPlayer.getPokemon().get(0).getHP());
        assertEquals(195, testPlayer.getPokemon().get(1).getHP());
    }

    @Test
    void purchasePokeballsTest() {
        testPlayer.addPokeDollars(500);
        assertEquals(500, testPlayer.getPokeDollars());
        assertEquals(0, testPlayer.getPokeballs());
        testGameModel.purchasePokeballs(4);
        assertEquals(100, testPlayer.getPokeDollars());
        assertEquals(4, testPlayer.getPokeballs());
    }

    @Test
    void writeToFileTest() throws IOException {
        testGameModel.writeToFile();
        testPlayer = testGameModel.loadGame();
        assertEquals(0, testPlayer.getPokeballs());
        assertEquals(0, testPlayer.getPokeDollars());
        assertEquals(0, testPlayer.getPokemon().size());

        testPlayer.addPokeballs(5);
        testPlayer.addPokeDollars(500);
        testPlayer.addPokemon(new BulbasaurStarter());
        testPlayer.addPokemon(new Pikachu(8));
        testGameModel.writeToFile();
    }
}
