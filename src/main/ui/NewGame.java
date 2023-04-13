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

    // MODIFIES: player
    // EFFECTS: gives intro and asks if the user wants to start a new game or load previous one and runs accordingly
    public Player runNewGame() throws IOException {
        System.out.println("Welcome to PokéMasters: The Unreal World of Pokémon");
        System.out.println("\n1. Start new game\n2. Load previous game");
        int game = input.nextInt();
        if (game == 1) {
            prologue();
            chooseStarter();
        } else if (game == 2) {
            player = gameModel.loadGame();
        }
        return player;
    }

    // EFFECTS: sets up new player
    private void endOfIntro() {
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

    // EFFECTS: gives options to choose starter pokemon from and moves the story accordingly
    private void chooseStarter() {
        System.out.println("In order to become a Pokémon Trainer, you need to first have a starter Pokémon.\n"
                + "There are 3 starter Pokémon\n\t"
                + "1. The Grass type: Bulbasaur\n\t2. The Fire type: Charmander\n\t3. The Water type: Squirtle\n"
                + "Which of these do you want to be your partner in this quest to become the PokéMaster?");

        int chooseStarter = input.nextInt() - 1;

        String starter = gameModel.assignStarter(chooseStarter);

        if (chooseStarter >= 0 && chooseStarter < 3) {
            System.out.println("You chose " + starter + "!");
            endOfIntro();
        } else if (chooseStarter == 294) {
            mysteriousPokemonAppearance();
        } else {
            System.err.println("Oops! Something went wrong.");
            System.out.println("You received a Pikachu!");
            endOfIntro();
        }
    }

    // EFFECTS: prints a clip of appearance of a mysterious pokemon (Mewtwo)
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