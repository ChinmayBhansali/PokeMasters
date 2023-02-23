package model;

import java.util.ArrayList;

public class Player {
    private ArrayList<Pokemon> pokemon;
    private int pokeballs;
    private int pokeDollars;

    public Player() {
        this.pokemon = new ArrayList<>();
        this.pokeballs = 0;
        this.pokeDollars = 0;
    }

    // MODIFIES: this.pokeballs
    // EFFECTS: adds given amount of pokeballs
    public void addPokeballs(int amount) {
        this.pokeballs += amount;
        System.out.println("You received " + amount + " PokéBalls.");
    }

    // MODIFIES: this.playerPokemon
    // EFFECTS: adds to given pokemon to the player's pokemon
    public void addPokemon(Pokemon newPokemon) {
        this.pokemon.add(newPokemon);
        System.out.println(newPokemon.getName() + " has been added to your Pokédex.");
    }

    // REQUIRES: this.playerPokemon contains freePokemon
    // MODIFIES: this.playerPokemon
    // EFFECTS: removes the pokemon from player's pokemon
    public void releasePokemon(Pokemon freePokemon) {
        this.pokemon.remove(freePokemon);
    }

    // REQUIRES: this.pokeballs > 0
    // MODIFIES: this.pokeballs
    // EFFECTS: reduces player's pokeballs by 1
    public void usePokeball() {
        this.pokeballs -= 1;
    }

    // MODIFIES: this.pokeDollars
    // EFFECTS: adds given amount to this.pokeDollars
    public void addPokeDollars(int amount) {
        this.pokeDollars += amount;
        System.out.println("You received " + amount + " PokéDollars.");
        System.out.println("Your PokéDollars: " + this.pokeDollars);
    }

    // REQUIRES: this.pokeDollars >= amount
    // MODIFIES: this.pokeDollars
    // EFFECTS: deducts given amount from this.pokeDollars
    public void deductPokeDollars(int amount) {
        this.pokeDollars -= amount;
        System.out.println("Your PokéDollars: " + this.pokeDollars);
    }

    public int getPokeballs() {
        return this.pokeballs;
    }

    public ArrayList<Pokemon> getPokemon() {
        return this.pokemon;
    }

    public int getPokeDollars() {
        return this.pokeDollars;
    }
}
