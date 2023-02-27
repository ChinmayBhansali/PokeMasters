package ui;

import model.Attack;
import model.Player;
import model.Pokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Battle {
    Player player;
    Pokemon wildPokemon;
    int pokemonNumber;
    int randomIndex;
    ArrayList<Integer> randomIndexList;
    Scanner input = new Scanner(System.in);

    public Battle(Player player, Pokemon wildPokemon) {
        this.player = player;
        this.wildPokemon = wildPokemon;
        pokemonNumber = 0;
        randomIndexList = new ArrayList<>();
        int i = 0;
        for (Attack a : wildPokemon.getAttacks()) {
            randomIndexList.add(i);
            i++;
        }
        battle();
    }

    // MODIFIES: this
    // EFFECTS: initiates battle, shuffles randomIndexList
    private void battle() {
        System.out.println("You encountered a wild " + wildPokemon.getName() + " of level "
                + wildPokemon.getLevel() + "!");

        while (player.getPokemon().get(pokemonNumber).getHP() > 0 && wildPokemon.getHP() > 0) {
            currentStats();

            int option = giveOptions();

            if (option == 1) {
                attack();
            } else if (option == 2) {
                switchPokemon();
            } else if (option == 3) {
                int use = useBag();
                if (use == 1) {
                    break;
                }
            } else if (option == 4) {
                break;
            }
        }
    }

    // EFFECTS: prints player's active pokemon's and wildPokemon's HPs
    private void currentStats() {
        System.out.println("Your " + player.getPokemon().get(pokemonNumber).getName() + "'s HP: "
                + player.getPokemon().get(pokemonNumber).getHP() + "\n" + wildPokemon.getName()
                + "'s HP: " + wildPokemon.getHP());
    }

    // EFFECTS: prints the options in a battle and returns user input integer
    private int giveOptions() {
        System.out.println("1. Attack");
        System.out.println("2. Switch");
        System.out.println("3. Bag");
        System.out.println("4. Escape");

        return input.nextInt();
    }

    // EFFECTS: initiates attacks
    private void attack() {
        playerPokemonAttack();
        if (wildPokemon.getHP() > 0) {
            wildPokemonAttack();
        }
    }

    // REQUIRES: 0 < input integer <= player.pokemon.size()
    // MODIFIES: this
    // EFFECTS: changes the pokemonNumber according to user input
    private void switchPokemon() {
        int i = 1;
        for (Pokemon p : player.getPokemon()) {
            System.out.println(i + ". " + p.getName());
            i++;
        }
        pokemonNumber = input.nextInt() - 1;
    }

    // MODIFIES: player
    // EFFECTS: if wildPokemon's health is critical, player uses pokeball and wildPokemon is added to player's pokemon
    private int useBag() {
        System.out.println("1. PokéBalls x" + player.getPokeballs());
        input.nextInt();
        System.out.println("1. Use");
        int usePokeball = input.nextInt();
        if (usePokeball == 1) {
            if (wildPokemon.isHealthCritical()) {
                catchPokemon();
                return 1;
            } else {
                catchPokemonFail();
            }
        }
        return 0;
    }

    // MODIFIES: wildPokemon
    // EFFECTS: if player's active pokemon's chosen attack power > wildPokemon's HP then
    //          wildPokemon's HP reduces by player's pokemon's attack power,
    //          otherwise wildPokemon's HP = 0
    private void playerPokemonAttack() {
        Scanner input = new Scanner(System.in);

        int i = 1;
        for (Attack a : player.getPokemon().get(pokemonNumber).getAttacks()) {
            System.out.println(i + ". " + a.getName());
            i++;
        }

        int whichAttack = input.nextInt() - 1;

        System.out.println("Your " + player.getPokemon().get(pokemonNumber).getName() + " used "
                + player.getPokemon().get(pokemonNumber).getAttacks().get(whichAttack).getName());

        if (wildPokemon.getHP()
                <= player.getPokemon().get(pokemonNumber).getAttacks().get(whichAttack).getPower()) {
            wildPokemon.reduceHP(wildPokemon.getHP());
            System.out.println("Your " + player.getPokemon().get(pokemonNumber).getName() + " defeated "
                    + wildPokemon.getName());
            player.addPokeDollars(10 * wildPokemon.getLevel());
        } else {
            wildPokemon.reduceHP(
                    player.getPokemon().get(pokemonNumber).getAttacks().get(whichAttack).getPower()
            );
            System.out.println("Your " + player.getPokemon().get(pokemonNumber).getName() + " dealt "
                    + player.getPokemon().get(pokemonNumber).getAttacks().get(whichAttack).getPower()
                    + " damage to " + wildPokemon.getName());
        }
    }

    // MODIFIES: player
    // EFFECTS: if wildPokemon's random attack's power > player's active pokemon's HP then
    //          reduces player's active pokemon's HP by the wildPokemon's attack power, otherwise
    //          player's active pokemon's HP = 0
    private void wildPokemonAttack() {
        Collections.shuffle(randomIndexList);
        randomIndex = randomIndexList.get(0);

        System.out.println(wildPokemon.getName() + " used " + wildPokemon.getAttacks().get(this.randomIndex).getName());

        if (player.getPokemon().get(pokemonNumber).getHP()
                <=
                wildPokemon.getAttacks().get(randomIndex).getPower()) {
            System.out.println(wildPokemon.getName() + " dealt "
                    + player.getPokemon().get(pokemonNumber).getHP() + " damage to your "
                    + player.getPokemon().get(pokemonNumber).getName());
            player.getPokemon().get(pokemonNumber).reduceHP(
                    player.getPokemon().get(pokemonNumber).getHP()
            );
            System.out.println("Your " + player.getPokemon().get(pokemonNumber).getName() + " fainted");
        } else {
            player.getPokemon().get(pokemonNumber).reduceHP(
                    wildPokemon.getAttacks().get(randomIndex).getPower()
            );
            System.out.println(wildPokemon.getName() + " dealt " + wildPokemon.getAttacks().get(randomIndex).getPower()
                    + " damage to your " + player.getPokemon().get(pokemonNumber).getName());
        }
    }

    // MODIFIES: player
    // EFFECTS: player uses pokeball and player gets wildPokemon
    private void catchPokemon() {
        player.usePokeball();
        System.out.println(wildPokemon.getName() + " was caught!");
        player.addPokemon(wildPokemon);
    }

    // MODIFIES: player
    // EFFECTS: player uses pokeball
    private void catchPokemonFail() {
        player.usePokeball();
        System.out.println("Failed to catch " + wildPokemon.getName());
    }
}