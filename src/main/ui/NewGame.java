package ui;

import model.GameModel;
import model.Player;

import java.io.IOException;
import java.util.Scanner;

import static model.GameModel.STARTING_POKEBALLS;
import static ui.Game.JSON_STORE;

public class NewGame {
    private Player player;
    private GameModel gameModel;
    private Scanner input = new Scanner(System.in);

    public NewGame() {
        player = new Player();
        gameModel = new GameModel(player, JSON_STORE);
    }

    public Player run() throws IOException {
        System.out.println("\n1. New game\n2. Load previous game");
        int game = input.nextInt();
        if (game == 1) {
            prologue();
            chooseStarter();
        } else if (game == 2) {
            player = gameModel.loadGame();
        }
        return player;
    }

    // MODIFIES: player
    // EFFECTS: initializes the NewGame, adds STARTING_POKEBALLS pokeballs to player and adds STARTING_POKEDOLLARS
    //          pokedollars to player
    private void newGame() {
        gameModel.readyPlayer();
        System.out.println("Now, now! You will need some PokéBalls to catch the Pokémon around you.\n"
                + "Here are 5 PokéBalls. You might need them. You can purchase more from any PokéCenter."
                + "You received " + STARTING_POKEBALLS + " PokéBalls.\nNow, set off to travel this amazing world!");
    }

    // EFFECTS: prints the prologue for the game
    private void prologue() {
        System.out.println("Welcome to the world of Pokémon!\n"
                + "So what exactly are Pokémon, you ask?\n"
                + "They are the strange creatures that live in the forests and lakes.\n"
                + "Pokémon are creatures of all shapes and sizes who live in the wild or alongside their human partners"
                + " (called \"Trainers\").\n"
                + "During their adventures, Pokémon grow and become more experienced and even, on occasion, evolve into"
                + " stronger Pokémon.\n"
                + "Hundreds of known Pokémon inhabit the Pokémon universe, with untold numbers waiting to be"
                + " discovered! [press Enter to continue]");

        input.nextLine();
        input.nextLine();

        System.out.println("Enough of the introduction. Now, let's dive into that world of Pokémon and show everyone "
                + "that you are the PokéMaster!");

        input.nextLine();
    }

    // MODIFIES: player
    // EFFECTS: adds the chosen pokemon to player.pokemon
    private void chooseStarter() {
        System.out.println("In order to become a Pokémon Trainer, you need to first have a starter Pokémon.\n"
                + "There are 3 starter Pokémon\n\t"
                + "1. The Grass type: Bulbasaur\n\t2. The Fire type: Charmander\n\t3. The Water type: Squirtle\n"
                + "Which of these do you want to be your partner in this quest to become the PokéMaster?");

        int chooseStarter = input.nextInt() - 1;

        String starter = gameModel.assignStarter(chooseStarter);

        if (chooseStarter >= 0 && chooseStarter < 3) {
            System.out.println("You chose " + starter + "!");
            newGame();
        } else if (chooseStarter == 294) {
            mysteriousPokemonAppearance();
        } else {
            System.err.println("Oops! Something went wrong.");
            System.out.println("You received a Pikachu!");
            newGame();
        }
    }

    private void mysteriousPokemonAppearance() {
        System.err.println("!!!!");
        input.nextLine();
        input.nextLine();
        System.err.println("???");
        input.nextLine();
        System.err.println("Something is going wrong!!");
        input.nextLine();
        System.err.println("A mysterious Pokémon appeared...");
        input.nextLine();
    }
}
