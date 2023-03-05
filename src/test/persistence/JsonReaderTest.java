package persistence;

import model.Player;
import model.Pokemon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {
    private Player player;

    @BeforeEach
    void setup() {
        player = new Player();
    }

    @Test
    void readerNonExistentFileTest() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            player = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void readerNewPlayerTest() {
        JsonReader reader = new JsonReader("./data/testReaderNewPlayer.json");
        try {
            player = reader.read();
            assertEquals(0, player.getPokeballs());
            assertEquals(0, player.getPokeDollars());
            assertEquals(0, player.getPokemon().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void readerGeneralPlayerTest() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlayer.json");
        try {
            player = reader.read();
            assertEquals(4, player.getPokeballs());
            assertEquals(520, player.getPokeDollars());
            ArrayList<Pokemon> pokemon = player.getPokemon();
            assertEquals(2, pokemon.size());
            checkPokemon("Bulbasaur", 5, 225, 2500, pokemon.get(0));
            checkPokemon("Slowpoke", 8, 0, 6440, pokemon.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}