package ui;

import model.Attack;
import model.BattleModel;
import model.Player;
import model.Pokemon;

import java.util.Scanner;

public class Battle {
    BattleModel battleModel;
    Scanner input = new Scanner(System.in);

    public Battle(Player player) {
        battleModel = new BattleModel(player);
        battle();
    }

    // MODIFIES: this
    // EFFECTS: initiates battle, shuffles randomIndexList
    private void battle() {
        System.out.println("You encountered a wild " + battleModel.getWildPokemon().getName() + " of level "
                + battleModel.getWildPokemon().getLevel() + "!");

        while (battleModel.getActivePokemon().getHP() > 0 && battleModel.getWildPokemon().getHP() > 0) {
            currentStats();

            int option = giveOptions();

            if (option == 1) {
                attack();
            } else if (option == 2) {
                switchPokemon();
            } else if (option == 3) {
                boolean use = useBag();
                if (use) {
                    break;
                }
            } else if (option == 4) {
                break;
            }
        }
    }

    // EFFECTS: prints player's active pokemon's and wildPokemon's HPs
    private void currentStats() {
        System.out.println("Your " + battleModel.getActivePokemon().getName() + "'s HP: "
                + battleModel.getActivePokemon().getHP() + "\n" + battleModel.getWildPokemon().getName()
                + "'s HP: " + battleModel.getWildPokemon().getHP());
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
        boolean attacked = playerAttack();
        if (battleModel.getWildPokemon().getHP() > 0 && attacked) {
            wildPokemonAttack();
        }
    }

    // REQUIRES: 0 < input integer <= player.pokemon.size()
    // MODIFIES: this
    // EFFECTS: changes the pokemonNumber according to user input
    private void switchPokemon() {
        int i = 1;
        for (Pokemon p : battleModel.getPlayer().getPokemon()) {
            System.out.println(i + ". " + p.getName());
            i++;
        }
        int pokemonNumber = input.nextInt() - 1;
        battleModel.switchPokemon(pokemonNumber);
    }

    // MODIFIES: player
    // EFFECTS: if wildPokemon's health is critical, player uses pokeball and wildPokemon is added to player's pokemon
    private boolean useBag() {
        System.out.println("1. PokéBalls x" + battleModel.getPlayer().getPokeballs() + "\n0. Back");
        int item = input.nextInt();
        System.out.println("1. Use\n0. Back");
        int usePokeball = input.nextInt();
        if (usePokeball == 1) {
            if (battleModel.usePokeball()) {
                System.out.println(battleModel.getWildPokemon().getName() + " was caught!");
                System.out.println(battleModel.getWildPokemon().getName() + " has been added to your Pokédex.");
                return true;
            } else {
                System.out.println("Failed to catch " + battleModel.getWildPokemon().getName() + "!");
            }
        }
        return false;
    }

    // MODIFIES: wildPokemon
    // EFFECTS: if player's active pokemon's chosen attack power > wildPokemon's HP then
    //          wildPokemon's HP reduces by player's pokemon's attack power,
    //          otherwise wildPokemon's HP = 0
    private boolean playerAttack() {
        int i = 1;
        for (Attack a : battleModel.getActivePokemon().getAttacks()) {
            System.out.println(i + ". " + a.getName());
            i++;
        }

        System.out.println("0. Back");
        int attackNumber = input.nextInt() - 1;
        if (attackNumber == -1) {
            return false;
        } else if (attackNumber >= 0 && attackNumber < battleModel.getActivePokemon().getAttacks().size()) {
            activePokemonAttack(attackNumber);
        } else {
            System.err.println("Invalid attack. Please try again.");
            playerAttack();
        }
        return true;
    }

    // MODIFIES: player
    // EFFECTS: if wildPokemon's random attack's power > player's active pokemon's HP then
    //          reduces player's active pokemon's HP by the wildPokemon's attack power, otherwise
    //          player's active pokemon's HP = 0
    private void wildPokemonAttack() {
        int originalHP = battleModel.getActivePokemon().getHP();
        Attack wildPokemonAttack = battleModel.wildPokemonAttack();

        System.out.println(battleModel.getWildPokemon().getName() + " used " + wildPokemonAttack.getName());

        if (battleModel.getActivePokemon().getHP() == 0) {
            System.out.println(battleModel.getWildPokemon().getName() + " dealt " + originalHP + " damage to your "
                    + battleModel.getActivePokemon().getName() + "\nYour "
                    + battleModel.getActivePokemon().getName() + " fainted");
        } else {
            System.out.println(battleModel.getWildPokemon().getName() + " dealt " + wildPokemonAttack.getPower()
                    + " damage to your " + battleModel.getActivePokemon().getName());
        }
    }

    private void activePokemonAttack(int attackNumber) {
        Attack whichAttack = battleModel.getActivePokemon().getAttacks().get(attackNumber);

        System.out.println("Your " + battleModel.getActivePokemon().getName() + " used "
                + whichAttack.getName());

        boolean defeated = battleModel.playerAttack(whichAttack);
        if (defeated) {
            System.out.println("Your " + battleModel.getActivePokemon().getName() + " defeated "
                    + battleModel.getWildPokemon().getName() + "\nYou received "
                    + (10 * battleModel.getWildPokemon().getLevel()) + " PokéDollars.\n" + "Your PokéDollars: "
                    + battleModel.getPlayer().getPokeDollars());
        } else {
            System.out.println("Your " + battleModel.getActivePokemon().getName() + " dealt "
                    + whichAttack.getPower() + " damage to " + battleModel.getWildPokemon().getName());
        }
    }
}