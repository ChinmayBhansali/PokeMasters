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
    }

    // EFFECTS: runs main battle
    public void fight() {
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

    // EFFECTS: prints active pokemon's and wildPokemon's HPs
    private void currentStats() {
        System.out.println("Your " + battleModel.getActivePokemon().getName() + "'s HP: "
                + battleModel.getActivePokemon().getHP() + "\n" + battleModel.getWildPokemon().getName()
                + "'s HP: " + battleModel.getWildPokemon().getHP());
    }

    // EFFECTS: prints the options in a battle and returns user input integer
    private int giveOptions() {
        System.out.println("\n1. Attack");
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

    // REQUIRES: 0 <= pokemonNumber < player.pokemon.size()
    // EFFECTS: gives list of player's pokemon and switches active pokemon
    private void switchPokemon() {
        int i = 1;
        for (Pokemon p : battleModel.getPlayer().getPokemon()) {
            System.out.println(i + ". " + p.getName());
            i++;
        }
        int pokemonNumber = input.nextInt() - 1;
        battleModel.switchPokemon(pokemonNumber);
    }

    // EFFECTS: player uses bag, uses pokeball to try and catch the wild pokemon
    private boolean useBag() {
        System.out.println("1. PokéBalls x" + battleModel.getPlayer().getPokeballs() + "\n0. Back");
        int item = input.nextInt();
        if (item == 1) {
            System.out.println("1. Use\n0. Back");
            int usePokeball = input.nextInt();
            if (usePokeball == 1) {
                int caught = battleModel.usePokeball();
                if (caught == 1) {
                    System.out.println("1. 2.. 3... Poof!");
                    System.out.println(battleModel.getWildPokemon().getName() + " was caught!");
                    System.out.println(battleModel.getWildPokemon().getName() + " has been added to your Pokédex.");
                    return true;
                } else if (caught == 0) {
                    System.out.println("1. 2.. 3... Bam!");
                    System.out.println("Failed to catch " + battleModel.getWildPokemon().getName() + "!");
                } else {
                    System.err.println("You cannot have more than 6 Pokémon in your team!");
                }
            }
        }
        return false;
    }

    // EFFECTS: returns false if input == 0, else attacks the wild pokemon with chosen attack and returns true
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

    // EFFECTS: wild pokemon attacks active pokemon and prints lines according to the result of the attack
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

    // EFFECTS: active pokemon attacks wild pokemon with chosen attack; prints lines according to result of the attack
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

        boolean levelUp = battleModel.activePokemonGainXP(whichAttack);
        System.out.println("Your " + battleModel.getActivePokemon().getName() + " gained " + whichAttack.getPower()
                    + " XP. (" + (battleModel.getActivePokemon().getXP() - whichAttack.getPower()) + " -> "
                    + battleModel.getActivePokemon().getXP() + ")");
        if (levelUp) {
            System.out.println("Your " + battleModel.getActivePokemon().getName() + " leveled up! ("
                    + (battleModel.getActivePokemon().getLevel() - 1) + " -> "
                    + battleModel.getActivePokemon().getLevel() + ")");
        }
    }
}