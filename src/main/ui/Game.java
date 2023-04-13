package ui;

import model.GameModel;
import model.Player;
import model.Pokemon;

import java.io.FileNotFoundException;
import java.util.*;

public class Game {
    private Player player;
    private GameModel gameModel;
    private Scanner input = new Scanner(System.in);
    public static final String JSON_STORE = "./data/pokemasters.json";


    public Game(Player player) {
        this.player = player;
        gameModel = new GameModel(player, JSON_STORE);
    }

    // EFFECTS: runs the main game
    public void runGame() {
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
        System.out.println("See you again!");
    }

    // EFFECTS: prints direction and movement of player, and if an encounter can happen then starts a new battle
    private void move() {
        System.out.println("1. North\n" + "2. West\n" + "3. East\n" + "4. South");
        int direction = input.nextInt() - 1;

        Random random = new Random();
        int randomSteps = random.nextInt(6) + 1;
        ArrayList<String> directions = new ArrayList<>(Arrays.asList("North", "West", "East", "South"));

        System.out.println("You moved " + randomSteps + " steps towards "
                + directions.get(direction) + ".");
        boolean willBattle = gameModel.encounter();
        if (willBattle) {
            Battle battle = new Battle(player);
            battle.fight();
        }
    }

    // EFFECTS: gives options to restore pokemon's health and buy items
    private void pokeCenter() {
        System.out.println("1. Restore your Pokémon's health\n2. Buy items\n0. Back");
        int inPokeCenter =  input.nextInt();
        if (inPokeCenter == 1) {
            pokemonCenter();
        }
        if (inPokeCenter == 2) {
            buyItems();
        }
    }

    // REQUIRES: player.pokemon.size() > 0
    // EFFECTS: runs getPokemonStats() and releasePokemon()
    private void yourPokemon() {
        getPokemonStats();
        releasePokemon();
    }

    // EFFECTS: prints all pokemon's HP after a full restore
    private void pokemonCenter() {
        gameModel.restorePokemonHealth();
        for (Pokemon p : player.getPokemon()) {
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

    // EFFECTS: purchases buyPokeballs pokeballs if player has sufficient pokedollars or gives out an error message
    private void buyPokeballs() {
        System.out.println("Your PokéDollars: " + player.getPokeDollars());
        System.out.println("1. Continue?\n0. Back");
        int buy = input.nextInt();
        if (buy == 1) {
            System.out.println("Amount: ");
            int buyPokeballs = input.nextInt();
            if (buyPokeballs * 100 <= player.getPokeDollars()) {
                gameModel.purchasePokeballs(buyPokeballs);
                System.out.println("You received " + buyPokeballs + " PokéBalls.");
            } else {
                System.err.println("Insufficient PokéDollars!");

            }
        }
    }

    // EFFECTS: if the user wants to save the game, does so
    private void quit() {
        System.out.println("Do you want to save your game?\n\t1. Yes\n\t2. No");
        int save = input.nextInt();
        if (save == 1) {
            try {
                gameModel.writeToFile();
                System.out.println("Game successfully saved!");
            } catch (FileNotFoundException e) {
                System.out.println("Unable to write to file");
            }
        }
    }

    // EFFECTS: prints name, level, HP, XP and Attacks of all pokemon
    private void getPokemonStats() {
        int i = 1;
        for (Pokemon p : player.getPokemon()) {
            System.out.println(i + ". " + p.getName() + ":\n\tLevel: " + p.getLevel() + "\n\tHP: " + p.getHP()
                    + "\n\tXP: " + p.getXP() + "\n\tAttacks: " + p.getAttackNames());
            i++;
        }
        System.out.println("0. Back");
    }

    // EFFECTS: releases the pokemon the user wants to from player
    private void releasePokemon() {
        int index = input.nextInt() - 1;
        if (index < player.getPokemon().size() && index >= 0) {
            Pokemon thisPokemon = player.getPokemon().get(index);
            if (player.getPokemon().contains(thisPokemon)) {
                System.out.println("1. Release\n0. Back");
                int pokemonToDo = input.nextInt();
                if (pokemonToDo == 1) {
                    gameModel.releasePokemon(thisPokemon);
                    System.out.println(thisPokemon.getName() + " has been removed from your Pokémon.");
                }
            }
        }
    }
}