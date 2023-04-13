package model.pokemon;

import model.Pokemon;
import model.attacks.Bite;
import model.attacks.Ember;
import model.attacks.FireFang;
import model.attacks.FlameWheel;

import java.util.ArrayList;
import java.util.Arrays;

public class Growlithe extends Pokemon {
    public Growlithe(int level) {
        super("Growlithe", level);
    }

    @Override
    public void setHP() {
        hp = 55 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Ember()));
        learnAttacks();
    }

    @Override
    protected void setPokedexNumber() {
        pokedexNumber = "058";
    }

    private void learnAttacks() {
        if (level >= 8) {
            attacks.add(new Bite());
        }
        if (level >= 12) {
            attacks.add(new FlameWheel());
        }
        if (level >= 24) {
            attacks.add(new FireFang());
        }
    }
}
