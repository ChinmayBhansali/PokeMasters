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
        int i = 0;
        for (Attack a : wildPokemon.getAttacks()) {
            randomIndexList.add(i);
            i++;
        }
        battle(player, wildPokemon);
    }

    // MODIFIES: randomIndexList
    // EFFECTS: initiates battle, shuffles randomIndexList
    private void battle(Player player, Pokemon wildPokemon) {
        while (player.getPokemon().get(this.pokemonNumber).getHP() > 0 && wildPokemon.getHP() > 0) {
            System.out.println("Your " + player.getPokemon().get(this.pokemonNumber).getName() + "'s HP: "
                    + player.getPokemon().get(this.pokemonNumber).getHP() + "\n" + wildPokemon.getName()
                    + "'s HP: " + wildPokemon.getHP());
            giveOptions();

            int option = input.nextInt();

            if (option == 1) {
                Collections.shuffle(this.randomIndexList);
                attack(player, wildPokemon);
            } else if (option == 2) {
                switchPokemon(player);
            } else if (option == 3) {
                int use = useBag(player, wildPokemon);
                if (use == 1) {
                    break;
                }
            } else if (option == 4) {
                break;
            }
        }
    }

    // EFFECTS: prints the options in a battle
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

    // MODIFIES: this.pokemonNumber
    // EFFECTS: changes the pokemonNumber according to user input
    private void switchPokemon(Player player) {
        int i = 1;
        for (Pokemon p : player.getPokemon()) {
            System.out.println(i + ". " + p.getName());
            i++;
        }
        this.pokemonNumber = input.nextInt() - 1;
    }

    // MODIFIES: player.pokeballs and player.pokemon
    // EFFECTS: if wildPokemon's health is critical, player.usePokeball and wildPokemon is added to player.pokemon
    private int useBag(Player player, Pokemon wildPokemon) {
        System.out.println("1. PokéBalls x" + player.getPokeballs());
        input.nextInt();
        System.out.println("1. Use");
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

    // MODIFIES: wildPokemon.HP
    // EFFECTS: if player.pokemon.get(pokemonNumber).attacks.get(whichAttack).power > wildPokemon.HP then
    //          wildPokemon.HP -= the attack power, otherwise wildPokemon.HP = 0
    private void playerPokemonAttack(Player player, Pokemon wildPokemon) {
        Scanner input = new Scanner(System.in);

        int i = 1;
        for (Attack a : player.getPokemon().get(this.pokemonNumber).getAttacks()) {
            System.out.println(i + ". " + a.getName());
            i++;
        }

        int whichAttack = input.nextInt() - 1;

        System.out.println("Your " + player.getPokemon().get(pokemonNumber).getName() + " used "
                + player.getPokemon().get(pokemonNumber).getAttacks().get(whichAttack).getName());

        if (wildPokemon.getHP()
                <= player.getPokemon().get(this.pokemonNumber).getAttacks().get(whichAttack).getPower()) {
            wildPokemon.reduceHP(wildPokemon.getHP());
            System.out.println("Your " + player.getPokemon().get(this.pokemonNumber).getName() + " defeated "
                    + wildPokemon.getName());
            player.addPokeDollars(10 * wildPokemon.getLevel());
        } else {
            wildPokemon.reduceHP(
                    player.getPokemon().get(this.pokemonNumber).getAttacks().get(whichAttack).getPower()
            );
            System.out.println("Your " + player.getPokemon().get(this.pokemonNumber).getName() + " dealt "
                    + player.getPokemon().get(this.pokemonNumber).getAttacks().get(whichAttack).getPower()
                    + " damage to " + wildPokemon.getName());
        }
    }

    // MODIFIES: player.pokemon.get(pokemonNumber).HP
    // EFFECTS: if wildPokemon.attack.get(randomIndex).power > player.pokemon.get(pokemonNumber).HP then
    //          player.pokemon.get(pokemonNumber).HP -= the attack power, otherwise
    //          player.pokemon.get(pokemonNumber).HP = 0
    private void wildPokemonAttack(Player player, Pokemon wildPokemon) {
        this.randomIndex = this.randomIndexList.get(0);

        System.out.println(wildPokemon.getName() + " used " + wildPokemon.getAttacks().get(this.randomIndex).getName());

        if (player.getPokemon().get(this.pokemonNumber).getHP()
                <=
                wildPokemon.getAttacks().get(randomIndex).getPower()) {
            System.out.println(wildPokemon.getName() + " dealt "
                    + player.getPokemon().get(this.pokemonNumber).getHP() + " damage to your "
                    + player.getPokemon().get(this.pokemonNumber).getName());
            player.getPokemon().get(this.pokemonNumber).reduceHP(
                    player.getPokemon().get(this.pokemonNumber).getHP()
            );
            System.out.println("Your " + player.getPokemon().get(pokemonNumber).getName() + " fainted");
        } else {
            player.getPokemon().get(this.pokemonNumber).reduceHP(
                    wildPokemon.getAttacks().get(randomIndex).getPower()
            );
            System.out.println(wildPokemon.getName() + " dealt " + wildPokemon.getAttacks().get(randomIndex).getPower()
                    + " damage to your " + player.getPokemon().get(this.pokemonNumber).getName());
        }

    }

    // MODIFIES: player.pokeballs and player.pokemon
    // EFFECTS: player.usePokeball and player.pokemon gets wildPokemon
    private void catchPokemon(Player player, Pokemon wildPokemon) {
        player.usePokeball();
        System.out.println(wildPokemon.getName() + " was caught!");
        player.addPokemon(wildPokemon);
    }

    // MODIFIES: player.pokeballs
    // EFFECTS: player.usePokeball
    private void catchPokemonFail(Player player, Pokemon wildPokemon) {
        player.usePokeball();
        System.out.println("Failed to catch " + wildPokemon.getName());
    }

    private void setPokemonNumber() {
        this.pokemonNumber = 0;
    }

}
