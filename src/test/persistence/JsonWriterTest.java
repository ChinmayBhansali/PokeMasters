package persistence;

import model.Player;
import model.Pokemon;
import model.pokemon.Pikachu;
import model.pokemon.starters.BulbasaurStarter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    private Player player;

    @BeforeEach
    void setup() {
        player = new Player();
    }

    @Test
    void writerInvalidFileTest() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void writerNewPlayerTest() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterNewPlayer.json");
            writer.open();
            writer.write(player);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNewPlayer.json");
            player = reader.read();
            assertEquals(0, player.getPokeballs());
            assertEquals(0, player.getPokeDollars());
            assertEquals(0, player.getPokemon().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void writerGeneralPlayerTest() {
        try {
            player.addPokeballs(5);
            player.addPokeDollars(500);
            player.addPokemon(new BulbasaurStarter());
            player.addPokemon(new Pikachu(8));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlayer.json");
            writer.open();
            writer.write(player);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlayer.json");
            player = reader.read();
            assertEquals(5, player.getPokeballs());
            assertEquals(500, player.getPokeDollars());
            ArrayList<Pokemon> pokemon = player.getPokemon();
            assertEquals(2, pokemon.size());
            checkPokemon("Bulbasaur", 5, 225, 2500, pokemon.get(0));
            checkPokemon("Pikachu", 8, 280, 6400, pokemon.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}