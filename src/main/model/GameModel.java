package model;

import model.pokemon.Mewtwo;
import model.pokemon.starters.BulbasaurStarter;
import model.pokemon.starters.CharmanderStarter;
import model.pokemon.starters.PikachuStarter;
import model.pokemon.starters.SquirtleStarter;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.Battle;

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

    public Player loadGame() throws IOException {
        player = jsonReader.read();
        return player;
    }

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

    public void readyPlayer() {
        player.addPokeballs(STARTING_POKEBALLS);
        player.addPokeDollars(STARTING_POKEDOLLARS);
    }

    public boolean encounter() {
        if (canBattle()) {
            return true;
        }
        return false;
    }

    public void releasePokemon(Pokemon thisPokemon) {
        player.releasePokemon(thisPokemon);
    }

    public void restorePokemonHealth() {
        for (Pokemon p : player.getPokemon()) {
            p.setHP();
        }
    }

    public void purchasePokeballs(int quantity) {
        player.addPokeballs(quantity);
        player.deductPokeDollars(quantity * 100);
    }

    public void writeToFile() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(player);
        jsonWriter.close();
    }

    private boolean canBattle() {
        for (Pokemon p : player.getPokemon()) {
            if (p.getHP() > 0) {
                return true;
            }
        }
        return false;
    }
}
