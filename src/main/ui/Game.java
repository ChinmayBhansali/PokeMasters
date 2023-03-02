package ui;

import model.Player;
import model.Pokemon;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.*;

public class Game {
    private static final String JSON_STORE = "./data/pokemasters.json";
    private Player player;
    private Scanner input = new Scanner(System.in);
    private Random random = new Random();
    private JsonWriter jsonWriter;

    public Game(Player player) {
        this.player = player;
        jsonWriter = new JsonWriter(JSON_STORE);
        game();
    }

    // EFFECTS: runs the main game
    private void game() {
        while (true) {
            System.out.println("\n1. Move\n"
                    + "2. PokéCenter\n"
                    + "3. Your Pokémon\n"
                    + "4. Quit");

            int choice = input.nextInt();

            if (choice == 1) {
                move();
            } else if (choice == 2) {
                pokeCenter();
            } else if (choice == 3) {
                yourPokemon();
            } else if (choice == 4) {
                quit();
                break;
            }
        }
    }

    // EFFECTS: prints direction and movement of player and starts a Battle with player
    private void move() {
        System.out.println("1. North\n" + "2. West\n" + "3. East\n" + "4. South");
        int direction = input.nextInt() - 1;

        int randomSteps = random.nextInt(6) + 1;
        ArrayList<String> directions = new ArrayList<>(Arrays.asList("North", "West", "East", "South"));

        System.out.println("You moved " + randomSteps + " steps towards "
                + directions.get(direction) + ".");
        if (canBattle()) {
            new Battle(player);
        }
    }

    // MODIFIES: player
    // EFFECTS: restores player's all pokemon's health or buy items
    private void pokeCenter() {
        System.out.println("1. Restore your Pokémon's health\n2. Buy items\n0. Back");
        int inPokeCenter =  input.nextInt();
        if (inPokeCenter == 1) {
            restorePokemonHealth();
        }
        if (inPokeCenter == 2) {
            buyItems();
        }
    }

    // REQUIRES: player.pokemon.size() > 0
    // MODIFIES: player
    // EFFECTS: releases chosen pokemon from player's pokemon if opted
    private void yourPokemon() {
        int i = 1;
        for (Pokemon p : player.getPokemon()) {
            System.out.println(i + ". " + p.getName() + ":\n\tLevel: " + p.getLevel() + "\n\tHP: " + p.getHP());
            i++;
        }
        System.out.println("0. Back");
        int index = input.nextInt() - 1;
        if (index < player.getPokemon().size() && index >= 0) {
            Pokemon thisPokemon = player.getPokemon().get(index);
            if (player.getPokemon().contains(thisPokemon)) {
                System.out.println("1. Release\n0. Back");
                int pokemonToDo = input.nextInt();
                if (pokemonToDo == 1) {
                    player.releasePokemon(thisPokemon);
                    System.out.println(thisPokemon.getName() + " has been removed from your Pokémon.");
                }
            }
        }
    }

    // MODIFIES: player
    // EFFECTS: sets player's all pokemon's health to default
    private void restorePokemonHealth() {
        for (Pokemon p : player.getPokemon()) {
            p.setHP();
            System.out.println(p.getName() + "'s HP: " + p.getHP());
        }
    }

    // EFFECTS: buy items
    private void buyItems() {
        System.out.println("1. PokéBalls (100 PokéDollars per PokéBall)\n"
                + "0. Back");
        int itemNumber = input.nextInt();
        if (itemNumber == 1) {
            buyPokeballs();
        }
    }

    // MODIFIES: player
    // EFFECTS: adds buyPokeballs pokeballs to player's pokeballs if player has sufficient pokedollars and
    //          reduces player's pokeballs by buyPokeballs * 100 if opted
    private void buyPokeballs() {
        System.out.println("Your PokéDollars: " + player.getPokeDollars());
        System.out.println("1. Continue?\n0. Back");
        int buy = input.nextInt();
        if (buy == 1) {
            System.out.println("Amount: ");
            int buyPokeballs = input.nextInt();
            if (buyPokeballs * 100 <= player.getPokeDollars()) {
                player.addPokeballs(buyPokeballs);
                player.deductPokeDollars(buyPokeballs * 100);
                System.out.println("You received " + buyPokeballs + " PokéBalls.");
            } else {
                System.err.println("Insufficient PokéDollars!");
            }
        }
    }

    private void quit() {
        System.out.println("Do you want to save your game?\n\t1. Yes\n\t2. No");
        int save = input.nextInt();
        if (save == 1) {
            save();
        }
    }

    private void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(player);
            jsonWriter.close();
            System.out.println("Saved your game to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
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
