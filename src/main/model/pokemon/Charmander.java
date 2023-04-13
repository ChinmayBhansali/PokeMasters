package model.pokemon;

import model.Pokemon;
import model.attacks.DragonBreath;
import model.attacks.Ember;
import model.attacks.FireFang;
import model.attacks.Scratch;

import java.util.ArrayList;
import java.util.Arrays;

public class Charmander extends Pokemon {
    public Charmander(int level) {
        super("Charmander", level);
    }

    @Override
    public void setHP() {
        hp = 39 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Scratch()));
        learnAttacks();
    }

    @Override
    protected void setPokedexNumber() {
        pokedexNumber = "004";
    }

    private void learnAttacks() {
        if (level >= 4) {
            attacks.add(new Ember());
        }
        if (level >= 12) {
            attacks.add(new DragonBreath());
        }
        if (level >= 17) {
            attacks.add(new FireFang());
        }
    }
}
