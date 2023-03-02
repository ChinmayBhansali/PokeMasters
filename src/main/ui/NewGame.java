package ui;

import model.Player;
import model.Pokemon;
import model.pokemon.starters.BulbasaurStarter;
import model.pokemon.starters.CharmanderStarter;
import model.pokemon.starters.PikachuStarter;
import model.pokemon.starters.SquirtleStarter;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NewGame {
    private static final String JSON_STORE = "./data/pokemasters.json";
    private Player player;
    private static int STARTING_POKEBALLS = 5;
    private static int STARTING_POKEDOLLARS = 500;
    private Scanner input = new Scanner(System.in);
    private JsonReader jsonReader;

    public NewGame() throws IOException {
        jsonReader = new JsonReader(JSON_STORE);
        player = new Player();
        game();
        Game game = new Game(player);
    }

    private void game() throws IOException {
        System.out.println("\n1. New game\n2. Load previous game");
        int game = input.nextInt();
        if (game == 1) {
            newGame();
        } else if (game == 2) {
            player = jsonReader.read();
        }
    }

    // MODIFIES: player
    // EFFECTS: initializes the NewGame, adds STARTING_POKEBALLS pokeballs to player and adds STARTING_POKEDOLLARS
    //          pokedollars to player
    private void newGame() {
        prologue();
        chooseStarter();

        System.out.println("Now, now! You will need some PokéBalls to catch the Pokémon around you.\n"
                + "Here are 5 PokéBalls. You might need them. You can purchase more from any PokéCenter.");
        player.addPokeballs(STARTING_POKEBALLS);
        System.out.println("You received " + STARTING_POKEBALLS + " PokéBalls.");
        player.addPokeDollars(STARTING_POKEDOLLARS);
        System.out.println("Now, set off to travel this amazing world!");
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
                + " discovered!");

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
                + "1. The Grass type: Bulbasaur\n\t"
                + "2. The Fire type: Charmander\n\t"
                + "3. The Water type: Squirtle\n"
                + "Which of these do you want to be your partner in this quest to become the PokéMaster?");

        int chooseStarter = input.nextInt() - 1;

        ArrayList<Pokemon> starterPokemon = new ArrayList<>(Arrays.asList(new BulbasaurStarter(),
                new CharmanderStarter(), new SquirtleStarter(), new PikachuStarter()));

        if (chooseStarter >= 0 && chooseStarter < 3) {
            System.out.println("You chose " + starterPokemon.get(chooseStarter).getName() + "!");
            System.out.println(starterPokemon.get(chooseStarter).getName() + " has been added to your Pokédex.");
            player.addPokemon(starterPokemon.get(chooseStarter));
        } else {
            System.err.println("Oops! Something went wrong. You received a Pikachu as your starter Pokémon!"
                    + "\nPikachu has been added to your Pokédex.");
            player.addPokemon(starterPokemon.get(3));
        }
    }
}
