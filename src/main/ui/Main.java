package ui;

import model.Player;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to PokéMasters: The Unreal World of Pokémon");
        NewGame newGame = new NewGame();
        Player player = newGame.run();
        Game game = new Game(player);
        game.runGame();
        System.out.println("See you again!");
    }
}