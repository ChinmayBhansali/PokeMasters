package model;

import model.pokemon.Mewtwo;
import model.pokemon.starters.BulbasaurStarter;
import model.pokemon.starters.CharmanderStarter;
import model.pokemon.starters.PikachuStarter;
import model.pokemon.starters.SquirtleStarter;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameModel {
    private Player player;
    public static final int STARTING_POKEBALLS = 5;
    public static final int STARTING_POKEDOLLARS = 500;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    public GameModel(Player player, String destination) {
        jsonReader = new JsonReader(destination);
        jsonWriter = new JsonWriter(destination);
        this.player = player;
    }

    // MODIFIES: player
    // EFFECTS: reads from file updates player and returns it; throws IOException if something goes wrong
    public Player loadGame() throws IOException {
        player = jsonReader.read();
        return player;
    }

    // MODIFIES: player
    // EFFECTS: adds the starter pokemon to player according to index given if 0 <= index < 3;
    //               a level 20 Mewtwo if index == 294 (something like a cheat code);
    //               a Pikachu starter pokemon if index is anything else;
    //          returns the added pokemon
    public String assignStarter(int index) {
        ArrayList<Pokemon> starterPokemon = new ArrayList<>(Arrays.asList(new BulbasaurStarter(),
                new CharmanderStarter(), new SquirtleStarter()));

        if (index >= 0 && index < 3) {
            player.addPokemon(starterPokemon.get(index));
            return starterPokemon.get(index).getName();
        } else if (index == 294) {
            player.addPokemon(new Mewtwo(20));
        } else {
            player.addPokemon(new PikachuStarter());
        }

        return player.getPokemon().get(0).getName();
    }

    // MODIFIES: player
    // EFFECTS: adds STARTING_POKEBALLS pokeballs and STARTING_POKEDOLLARS pokedollars to player
    public void readyPlayer() {
        player.addPokeballs(STARTING_POKEBALLS);
        player.addPokeDollars(STARTING_POKEDOLLARS);
    }

    // EFFECTS: returns true if player can battle, false otherwise
    public boolean encounter() {
        if (canBattle()) {
            return true;
        }
        return false;
    }

    // MODIFIES: player
    // EFFECTS: removes this pokemon from player
    public void releasePokemon(Pokemon thisPokemon) {
        player.releasePokemon(thisPokemon);
    }

    // MODIFIES: player
    // EFFECTS: sets player's all pokemon's HP to default
    public void restorePokemonHealth() {
        for (Pokemon p : player.getPokemon()) {
            p.setHP();
        }
    }

    // MODIFIES: player
    // EFFECTS: adds buyPokeballs pokeballs to player's pokeballs and reduces player's pokeballs by buyPokeballs * 100
    public void purchasePokeballs(int quantity) {
        player.addPokeballs(quantity);
        player.deductPokeDollars(quantity * 100);
    }

    // MODIFIES: writes player to file; throws FileNotFoundException if error occurs
    public void writeToFile() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(player);
        jsonWriter.close();
    }

    // EFFECTS: returns true if any of player's pokemon has HP > 0, false otherwise
    private boolean canBattle() {
        for (Pokemon p : player.getPokemon()) {
            if (p.getHP() > 0) {
                return true;
            }
        }
        return false;
    }
}
