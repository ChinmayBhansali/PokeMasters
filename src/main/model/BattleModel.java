package model;

import model.pokemon.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BattleModel {
    Player player;
    Pokemon wildPokemon;
    int pokemonNumber;
    Random random;
    int randomIndex;

    public BattleModel(Player player) {
        this.player = player;
        this.wildPokemon = getRandomPokemon();
        pokemonNumber = 0;
        random = new Random();
    }

    // MODIFIES: wildPokemon
    // EFFECTS: if player's active pokemon's chosen attack power > wildPokemon's HP then
    //          wildPokemon's HP reduces by player's pokemon's attack power,
    //          otherwise wildPokemon's HP = 0
    public boolean playerAttack(Attack playerAttack) {
        if (wildPokemon.getHP() <= playerAttack.getPower()) {
            wildPokemon.reduceHP(wildPokemon.getHP());
            player.addPokeDollars(10 * wildPokemon.getLevel());
            return true;
        } else {
            wildPokemon.reduceHP(playerAttack.getPower());
            return false;
        }
    }

    // MODIFIES: player
    // EFFECTS: if wildPokemon's random attack's power > player's active pokemon's HP then
    //          reduces player's active pokemon's HP by the wildPokemon's attack power, otherwise
    //          player's active pokemon's HP = 0
    public Attack wildPokemonAttack() {
        randomIndex = random.nextInt(wildPokemon.getAttacks().size());

        player.getPokemon().get(pokemonNumber).reduceHP(Math.min(player.getPokemon().get(pokemonNumber).getHP(),
                wildPokemon.getAttacks().get(randomIndex).getPower()));

        return wildPokemon.getAttacks().get(randomIndex);
    }

    // MODIFIES: this
    // EFFECTS: changes the pokemonNumber according to given integer
    public void switchPokemon(int pokemonNumber) {
        this.pokemonNumber = pokemonNumber;
    }

    // MODIFIES: player
    // EFFECTS: player uses pokeball AND if wildPokemon's health is critical, player catches the pokemon
    public boolean usePokeball() {
        if (player.getPokemon().size() < 6) {
            if (wildPokemon.isHealthCritical()) {
                catchPokemon();
                return true;
            } else {
                catchPokemonFail();
            }
        } else {
            System.err.println("You cannot have more than 6 Pokémon in your team!");
        }
        return false;
    }

    // MODIFIES: pokemonNumber
    // EFFECTS: if active pokemon is fainted, switches to next pokemon (if there) AND returns active pokemon
    public Pokemon getActivePokemon() {
        if (player.getPokemon().get(pokemonNumber).getHP() == 0) {
            int i = 0;
            for (Pokemon playerPokemon : player.getPokemon()) {
                if (playerPokemon.getHP() > 0) {
                    switchPokemon(i);
                    break;
                }
                i++;
            }
        }
        return player.getPokemon().get(pokemonNumber);
    }

    public Pokemon getWildPokemon() {
        return wildPokemon;
    }

    public Player getPlayer() {
        return player;
    }

    // EFFECTS: returns a random pokemon (out of 15 pokemon) with a random level (1-10)
    private Pokemon getRandomPokemon() {
        Random random = new Random();
        int randomLevel = random.nextInt(10) + 1;
        ArrayList<Pokemon> allPokemon = new ArrayList<>(Arrays.asList(new Bulbasaur(randomLevel),
                new Charmander(randomLevel), new Squirtle(randomLevel), new Caterpie(randomLevel),
                new Weedle(randomLevel), new Pidgey(randomLevel), new Rattata(randomLevel),
                new Spearow(randomLevel), new Pikachu(randomLevel), new Diglett(randomLevel),
                new Meowth(randomLevel), new Psyduck(randomLevel), new Growlithe(randomLevel),
                new Slowpoke(randomLevel), new Onix(randomLevel)));

        int randomIndex = random.nextInt(allPokemon.size());
        return allPokemon.get(randomIndex);
    }

    // MODIFIES: player
    // EFFECTS: player uses pokeball and player gets wildPokemon
    private void catchPokemon() {
        player.usePokeball();
        player.addPokemon(wildPokemon);
    }

    // MODIFIES: player
    // EFFECTS: player uses pokeball
    private void catchPokemonFail() {
        player.usePokeball();
    }
}