package ui;

import model.Attack;
import model.Player;
import model.Pokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Battle {

    int pokemonNumber;
    int randomIndex;
    ArrayList<Integer> randomIndexList;
    Scanner input = new Scanner(System.in);

    public Battle(Player player, Pokemon wildPokemon) {
        setPokemonNumber();
        randomIndexList = new ArrayList<>();
        input = new Scanner(System.in);
        int i = 0;
        for (Attack a : wildPokemon.getAttacks()) {
            randomIndexList.add(i);
            i++;
        }
        battle(player, wildPokemon);
    }

    private void battle(Player player, Pokemon wildPokemon) {
        while (player.getPlayerPokemon().get(this.pokemonNumber).getHP() > 0 && wildPokemon.getHP() > 0) {
            System.out.println("Your " + player.getPlayerPokemon().get(this.pokemonNumber).getName() + "'s HP: "
                    + player.getPlayerPokemon().get(this.pokemonNumber).getHP() + "\n" + wildPokemon.getName()
                    + "'s HP: " + wildPokemon.getHP());
            giveOptions();

            int option = input.nextInt();

            if (option == 1) {
                Collections.shuffle(this.randomIndexList);
                attack(player, wildPokemon);
            } else if (option == 2) {
                switchPokemon(player);
            } else if (option == 3) {
                int flag = useBag(player, wildPokemon);
                if (flag == 1) {
                    break;
                }
            } else if (option == 4) {
                break;
            }
        }
    }

    private void giveOptions() {
        System.out.println("1. Attack");
        System.out.println("2. Switch");
        System.out.println("3. Bag");
        System.out.println("4. Escape");
    }

    private void attack(Player player, Pokemon wildPokemon) {
        playerPokemonAttack(player, wildPokemon);
        if (wildPokemon.getHP() > 0) {
            wildPokemonAttack(player, wildPokemon);
        }
    }

    private void switchPokemon(Player player) {
        int i = 1;
        for (Pokemon p : player.getPlayerPokemon()) {
            System.out.println(i + ". " + p.getName());
            i++;
        }
        this.pokemonNumber = input.nextInt() - 1;
    }

    private int useBag(Player player, Pokemon wildPokemon) {
        System.out.println("You have " + player.getPokeballs() + " pokeballs left\n" + "1. Use pokeball");
        int usePokeball = input.nextInt();
        if (usePokeball == 1) {
            if (wildPokemon.isHealthCritical()) {
                catchPokemon(player, wildPokemon);
                return 1;
            } else {
                catchPokemonFail(player, wildPokemon);
            }
        }
        return 0;
    }

    private void playerPokemonAttack(Player player, Pokemon wildPokemon) {
        Scanner input = new Scanner(System.in);

        int i = 1;
        for (Attack a : player.getPlayerPokemon().get(this.pokemonNumber).getAttacks()) {
            System.out.println(i + ". " + a.getName());
            i++;
        }

        int whichAttack = input.nextInt() - 1;

        System.out.println("Your " + player.getPlayerPokemon().get(pokemonNumber).getName() + " used "
                + player.getPlayerPokemon().get(pokemonNumber).getAttacks().get(whichAttack).getName());

        if (wildPokemon.getHP()
                <=
                player.getPlayerPokemon().get(this.pokemonNumber).getAttacks().get(whichAttack).getPower()) {
            wildPokemon.reduceHP(wildPokemon.getHP());
            System.out.println(player.getPlayerPokemon().get(this.pokemonNumber).getName() + " defeated "
                    + wildPokemon.getName());
        } else {
            wildPokemon.reduceHP(
                    player.getPlayerPokemon().get(this.pokemonNumber).getAttacks().get(whichAttack).getPower()
            );
            System.out.println("Your " + player.getPlayerPokemon().get(this.pokemonNumber).getName() + " dealt "
                    + player.getPlayerPokemon().get(this.pokemonNumber).getAttacks().get(whichAttack).getPower()
                    + " damage to " + wildPokemon.getName());
        }
    }

    private void wildPokemonAttack(Player player, Pokemon wildPokemon) {
        this.randomIndex = this.randomIndexList.get(0);

        System.out.println(wildPokemon.getName() + " used " + wildPokemon.getAttacks().get(this.randomIndex).getName());

        if (player.getPlayerPokemon().get(this.pokemonNumber).getHP()
                <=
                wildPokemon.getAttacks().get(randomIndex).getPower()) {
            System.out.println(wildPokemon.getName() + " dealt "
                    + player.getPlayerPokemon().get(this.pokemonNumber).getHP() + " damage to your "
                    + player.getPlayerPokemon().get(this.pokemonNumber).getName());
            player.getPlayerPokemon().get(this.pokemonNumber).reduceHP(
                    player.getPlayerPokemon().get(this.pokemonNumber).getHP()
            );
            System.out.println("Your " + player.getPlayerPokemon().get(pokemonNumber).getName() + " fainted");
        } else {
            player.getPlayerPokemon().get(this.pokemonNumber).reduceHP(
                    wildPokemon.getAttacks().get(randomIndex).getPower()
            );
            System.out.println(wildPokemon.getName() + " dealt " + wildPokemon.getAttacks().get(randomIndex).getPower()
                    + " damage to your " + player.getPlayerPokemon().get(this.pokemonNumber).getName());
        }

    }

    private void catchPokemon(Player player, Pokemon wildPokemon) {
        player.usePokeball();
        player.addPokemon(wildPokemon);
        System.out.println(wildPokemon.getName() + " was caught!");
    }

    private void catchPokemonFail(Player player, Pokemon wildPokemon) {
        player.usePokeball();
        System.out.println("Failed to catch " + wildPokemon.getName());
    }

    private void setPokemonNumber() {
        this.pokemonNumber = 0;
    }

}
