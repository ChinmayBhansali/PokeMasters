package ui;

import model.Player;
import model.pokemon.Pikachu;
import model.pokemon.starters.BulbasaurStarter;
import model.pokemon.starters.CharmanderStarter;
import model.pokemon.starters.PikachuStarter;
import model.pokemon.starters.SquirtleStarter;

import java.util.Scanner;

public class NewGame {
    private Player player;

    public NewGame() {
        player = new Player();
        newGame();
        Game game = new Game(player);
    }

    public void newGame() {
        prologue();
        chooseStarter();

        System.out.println("Now, now! You will need some pokéballs to catch the pokémon around you.\n"
                + "Here are 5 pokéballs. You might need them. You can purchase more from any PokéCenter.");
        player.addPokeballs(5);

        System.out.println("Now, set off to travel this amazing world!");
    }

    private void prologue() {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the world of Pokémon!\n"
                + "So what exactly are pokémon, you ask?\n"
                + "They are the strange creatures that live in the forests and lakes.\n"
                + "Pokémon are creatures of all shapes and sizes who live in the wild or alongside their human partners"
                + " (called \"Trainers\").\n"
                + "During their adventures, Pokémon grow and become more experienced and even, on occasion, evolve into"
                + " stronger Pokémon.\n"
                + "Hundreds of known Pokémon inhabit the Pokémon universe, with untold numbers waiting to be"
                + " discovered!");

        input.nextLine();

        System.out.println("Enough of the introduction, I guess. Now, let's dive into that world of pokémon and show"
                + " everyone that you are the PokéMaster!");

        input.nextLine();
    }

    private void chooseStarter() {
        Scanner input = new Scanner(System.in);
        System.out.println("In order to become a Pokémon Trainer, you need to first have a starter Pokémon.\n"
                + "There are 3 starter Pokémon\n"
                + "1. The Grass type: Bulbasaur\n"
                + "2. The Fire type: Charmander\n"
                + "3. The Water type: Squirtle\n\n"
                + "Which of these do you want to be your partner in this quest to become the PokéMaster?");

        int chooseStarter = input.nextInt();

        if (chooseStarter == 1) {
            System.out.println("You chose Bulbasaur!\n" + "Bulbasaur has been added to your Pokédex");
            player.addPokemon(new BulbasaurStarter());
        } else if (chooseStarter == 2) {
            System.out.println("You chose Charmander!\n" + "Charmander has been added to your Pokédex");
            player.addPokemon(new CharmanderStarter());
        } else if (chooseStarter == 3) {
            System.out.println("You chose Squirtle!\n" + "Squirtle has been added to your Pokédex");
            player.addPokemon(new SquirtleStarter());
        } else {
            System.err.println("Oops! Something went wrong. You got a Pikachu as your starter Pokémon!");
            System.out.println("Pikachu has been added to your Pokédex");
            player.addPokemon(new PikachuStarter());
        }
    }
}
