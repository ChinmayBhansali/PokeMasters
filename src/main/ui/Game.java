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

    // EFFECTS: prints direction and movement of player and make a Battle object with player and random pokemon of
    //          random level
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
        System.out.println("You encountered a wild " + allPokemon.get(0).getName() + " of level "
                + allPokemon.get(0).getLevel() + "!");
        Battle battle = new Battle(player, allPokemon.get(0));
    }

    // MODIFIES: player.playerPokemon
    // EFFECTS: sets all of player's pokemon's HP to original HP
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
    // MODIFIES: player.pokemon
    // EFFECTS: releases chosen pokemon from player.pokemon if opted
    private void yourPokemon() {
        int i = 1;
        for (Pokemon p : player.getPokemon()) {
            System.out.println(i + ". " + p.getName() + ":\n    Level: " + p.getLevel() + "\n    HP: " + p.getHP());
            i++;
        }
        System.out.println("0. Back");
        Pokemon thisPokemon = player.getPokemon().get(input.nextInt() - 1);
        if (player.getPokemon().contains(thisPokemon)) {
            System.out.println("1. Release\n0. Back");
            int pokemonToDo = input.nextInt();
            if (pokemonToDo == 1) {
                player.releasePokemon(thisPokemon);
            }
        }
    }

    // MODIFIES: player.pokemon
    // EFFECTS: restores health of all pokemon in player.pokemon
    private void restorePokemonHealth() {
        for (Pokemon p : player.getPokemon()) {
            p.restoreHealth();
            System.out.println(p.getName() + "'s HP: " + p.getHP());
        }
    }

    // MODIFIES: player.pokeDollars and player.pokeballs
    // EFFECTS: adds buyPokeballs pokeballs to player.pokeballs and reduces player.pokeballs by buyPokeballs * 100 if
    //          opted
    private void buyItems() {
        System.out.println("1. PokéBalls (100 PokéDollars per PokéBall)\n0. Back");
        int itemNumber = input.nextInt();
        if (itemNumber == 1) {
            System.out.println("Your PokéDollars: " + player.getPokeDollars());
            System.out.println("1. Continue?");
            int buy = input.nextInt();
            if (buy == 1) {
                System.out.println("Amount: ");
                int buyPokeballs = input.nextInt();
                if (buyPokeballs * 100 <= player.getPokeDollars()) {
                    player.deductPokeDollars(buyPokeballs * 100);
                    player.addPokeballs(buyPokeballs);
                } else {
                    System.err.println("Insufficient PokéDollars!");
                }
            }
        }
    }
}
