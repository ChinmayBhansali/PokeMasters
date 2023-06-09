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
        wildPokemon = getRandomPokemon();
        pokemonNumber = 0;
        random = new Random();
        EventLog.getInstance().logEvent(new Event(player + " started a new battle against a wild " + wildPokemon));
    }

    // MODIFIES: wildPokemon
    // EFFECTS: if player's active pokemon's chosen attack power > wildPokemon's HP then
    //          wildPokemon's HP reduces by player's pokemon's attack power and return true,
    //          otherwise wildPokemon's HP = 0 and returns false
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
    //          player's active pokemon's HP = 0; returns the attack used
    public Attack wildPokemonAttack() {
        randomIndex = random.nextInt(wildPokemon.getAttacks().size());

        player.getPokemon().get(pokemonNumber).reduceHP(Math.min(player.getPokemon().get(pokemonNumber).getHP(),
                wildPokemon.getAttacks().get(randomIndex).getPower()));

        return wildPokemon.getAttacks().get(randomIndex);
    }

    // MODIFIES: player
    // EFFECTS: active pokemon gains XP and if gained enough, levels up and returns true, otherwise returns false
    public boolean activePokemonGainXP(Attack attack) {
        player.getPokemon().get(pokemonNumber).gainXP(attack.getPower());
        boolean levelUp = player.getPokemon().get(pokemonNumber).levelUp();
        return levelUp;
    }

    // MODIFIES: player
    // EFFECTS: if active pokemon learns new attack then returns true, otherwise returns false
    public boolean activePokemonLearnAttack() {
        int originalAttacks = player.getPokemon().get(pokemonNumber).getAttacks().size();
        player.getPokemon().get(pokemonNumber).setAttacks();
        if (player.getPokemon().get(pokemonNumber).getAttacks().size() > originalAttacks) {
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: changes the pokemonNumber according to given integer
    public void switchPokemon(int pokemonNumber) {
        this.pokemonNumber = pokemonNumber;
    }

    // EFFECTS: if player has less than 6 pokemon and non-zero pokeballs,
    //          player catches the pokemon if its health is critical and returns 1, else fails to catch and returns 0,
    //          otherwise returns 2
    public int usePokeball() {
        if (player.getPokemon().size() < 6 && player.getPokeballs() > 0) {
            if (wildPokemon.isHealthCritical()) {
                catchPokemon();
                return 1;
            } else {
                catchPokemonFail();
                return 0;
            }
        }
        return 2;
    }

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

    // EFFECTS: returns a random pokemon (out of 15 pokemon) of a random level (1-15)
    private Pokemon getRandomPokemon() {
        Random random = new Random();
        int randomLevel = random.nextInt(15) + 1;
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