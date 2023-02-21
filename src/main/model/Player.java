package model;

import java.util.ArrayList;

public class Player {
    private ArrayList<Pokemon> playerPokemon;
    private int pokeballs;
//    private int pokeDollars;

    public Player() {
        this.playerPokemon = new ArrayList<>();
        this.pokeballs = 0;
    }

    // MODIFIES: this.pokeballs
    // EFFECTS: adds given amount of pokeballs
    public void addPokeballs(int amount) {
        this.pokeballs += amount;
        System.out.println("You received " + amount + " pokeballs.");
    }

    // MODIFIES: this.playerPokemon
    // EFFECTS: adds to given pokemon to the player's pokemon
    public void addPokemon(Pokemon newPokemon) {
        this.playerPokemon.add(newPokemon);
    }

//    // REQUIRES: this.playerPokemon contains freePokemon
//    // MODIFIES: this.playerPokemon
//    // EFFECTS: removes the pokemon from player's pokemon
//    public void releasePokemon(Pokemon freePokemon) {
//        this.playerPokemon.remove(freePokemon);
//    }

    // REQUIRES: this.pokeballs > 0
    // MODIFIES: this.pokeballs
    // EFFECTS: reduces player's pokeballs by 1
    public void usePokeball() {
        this.pokeballs -= 1;
    }

    public int getPokeballs() {
        return this.pokeballs;
    }

    public ArrayList<Pokemon> getPlayerPokemon() {
        return this.playerPokemon;
    }
}
