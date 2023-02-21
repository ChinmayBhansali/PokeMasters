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

    public void game() {
        while (true) {
            System.out.println("1. Move\n"
                    + "2. PokeCenter\n"
                    + "3. Your Pokemon\n"
                    + "4. Quit");

            int choice = input.nextInt();

            if (choice == 1) {
                move();
            } else if (choice == 2) {
                pokecenter();
            } else if (choice == 3) {
                for (Pokemon p : player.getPlayerPokemon()) {
                    System.out.println(p.getName() + ":\n    Level: " + p.getLevel() + "\n    HP: " + p.getHP());
                }
            } else if (choice == 4) {
                break;
            }
        }
    }

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

    private void pokecenter() {
        System.out.println("1. Restore your pokémon's health");
        input.nextInt();
        for (Pokemon p : player.getPlayerPokemon()) {
            p.restoreHealth();
            System.out.println(p.getName() + "'s HP: " + p.getHP());
        }
    }
}
