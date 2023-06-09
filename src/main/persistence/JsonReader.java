package persistence;

import model.Player;
import model.Pokemon;
import model.pokemon.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import model.pokemon.Mewtwo;
import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads player from file and returns it;
    //          throws IOException if an error occurs reading data from file
    public Player read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        Player player = parsePlayer(jsonObject);
        return player;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses player from JSON object and returns it
    private Player parsePlayer(JSONObject jsonObject) {
        int pokeballs = jsonObject.getInt("pokeballs");
        int pokedollars = jsonObject.getInt("pokedollars");
        Player player = new Player();
        player.addPokeballs(pokeballs);
        player.addPokeDollars(pokedollars);
        addPokemon(player, jsonObject);
        return player;
    }

    // MODIFIES: player
    // EFFECTS: parses pokemon from JSON object and adds them to player
    private void addPokemon(Player player, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pokemon");
        for (Object json : jsonArray) {
            JSONObject nextPokemon = (JSONObject) json;
            addPlayerPokemon(player, nextPokemon);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses pokemon from JSON object and adds it to player
    private void addPlayerPokemon(Player player, JSONObject jsonObject) {
        int level = jsonObject.getInt("level");
        String name = jsonObject.getString("name");
        int hp = jsonObject.getInt("hp");
        int xp = jsonObject.getInt("xp");
        ArrayList<Pokemon> allPokemon = new ArrayList<>(Arrays.asList(new Bulbasaur(level), new Caterpie(level),
                new Charmander(level), new Diglett(level), new Growlithe(level), new Meowth(level), new Mewtwo(level),
                new Onix(level), new Pidgey(level), new Pikachu(level), new Psyduck(level), new Rattata(level),
                new Slowpoke(level), new Spearow(level), new Squirtle(level), new Weedle(level)));
        ArrayList<String> allPokemonNames = new ArrayList<>();
        for (Pokemon p : allPokemon) {
            allPokemonNames.add(p.getName());
        }
        Pokemon pokemon = allPokemon.get(allPokemonNames.indexOf(name));
        pokemon.gainXP(xp - pokemon.getXP());
        pokemon.reduceHP(pokemon.getHP() - hp);
        player.addPokemon(pokemon);
    }
}