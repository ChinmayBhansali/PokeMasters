package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Player implements Writable {
    private ArrayList<Pokemon> pokemon;
    private int pokeballs;
    private int pokeDollars;
//    private ArrayList<Pokemon> pokedex;

    public Player() {
        this.pokemon = new ArrayList<>();
        this.pokeballs = 0;
        this.pokeDollars = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds given amount of pokeballs to player's pokeballs
    public void addPokeballs(int pokeballs) {
        this.pokeballs += pokeballs;
    }

    // MODIFIES: this
    // EFFECTS: adds to given pokemon to the player's pokemon
    public void addPokemon(Pokemon newPokemon) {
        this.pokemon.add(newPokemon);
    }

    // REQUIRES: this.pokemon contains freePokemon
    // MODIFIES: this
    // EFFECTS: removes the pokemon from player's pokemon
    public void releasePokemon(Pokemon freePokemon) {
        this.pokemon.remove(freePokemon);
    }

    // REQUIRES: this.pokeballs > 0
    // MODIFIES: this
    // EFFECTS: reduces player's pokeballs by 1
    public void usePokeball() {
        if (this.pokeballs <= 0) {
            System.err.println("You have no Pokéballs left.");
        } else {
            this.pokeballs -= 1;
        }
    }

    // MODIFIES: this.pokeDollars
    // EFFECTS: adds given amount to player's pokeDollars
    public void addPokeDollars(int pokeDollars) {
        this.pokeDollars += pokeDollars;
    }

    // REQUIRES: this.pokeDollars >= amount
    // MODIFIES: this.pokeDollars
    // EFFECTS: deducts given amount from player's pokeDollars
    public void deductPokeDollars(int pokeDollars) {
        this.pokeDollars -= pokeDollars;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pokemon", pokemonToJson());
        json.put("pokeballs", pokeballs);
        json.put("pokedollars", pokeDollars);
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray pokemonToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pokemon p : pokemon) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
