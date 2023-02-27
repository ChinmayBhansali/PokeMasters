package ui;

import model.Player;
import model.Pokemon;
import model.pokemon.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private Player player;
    private Scanner input = new Scanner(System.in);

    public Game(Player player) {
        this.player = player;
        game();
    }

    // EFFECTS: runs the main game
    public void game() {
        while (true) {
            System.out.println("1. Move\n"
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
                break;
            }
        }
    }

    // EFFECTS: prints direction and movement of player and creates a Battle with player and random pokemon of random
    //          level
    private void move() {
        ArrayList<String> directions = new ArrayList<>(Arrays.asList("North", "West", "East", "South"));
        System.out.println("1. North\n" + "2. West\n" + "3. East\n" + "4. South");
        int direction = input.nextInt() - 1;

        System.out.println("You moved " + (int) ((Math.random() * 6) + 1) + " steps towards "
                + directions.get(direction) + ".");

        int randomLevel = (int) (Math.random() * 10) + 1;
        ArrayList<Pokemon> allPokemon = new ArrayList<>(Arrays.asList(new Bulbasaur(randomLevel),
                new Charmander(randomLevel), new Squirtle(randomLevel), new Caterpie(randomLevel),
                new Weedle(randomLevel), new Pidgey(randomLevel), new Rattata(randomLevel),
                new Spearow(randomLevel), new Pikachu(randomLevel), new Diglett(randomLevel),
                new Meowth(randomLevel), new Psyduck(randomLevel), new Growlithe(randomLevel),
                new Slowpoke(randomLevel), new Onix(randomLevel)));
        Collections.shuffle(allPokemon);

        Battle battle = new Battle(player, allPokemon.get(0));
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
            System.out.println(i + ". " + p.getName() + ":\n    Level: " + p.getLevel() + "\n    HP: " + p.getHP());
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
            } else {
                System.err.println("Insufficient PokéDollars!");
            }
        }
    }
}
