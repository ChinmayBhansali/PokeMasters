package model.pokemon;

import model.Pokemon;
import model.attacks.Confusion;
import model.attacks.FurySwipes;
import model.attacks.Scratch;
import model.attacks.WaterGun;

import java.util.ArrayList;
import java.util.Arrays;

public class Psyduck extends Pokemon {
    public Psyduck(int level) {
        super("Psyduck", level);
    }

    @Override
    public void setHP() {
        hp = 50 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Scratch()));
        learnAttacks();
    }

    @Override
    protected void setPokedexNumber() {
        pokedexNumber = "054";
    }

    private void learnAttacks() {
        if (level >= 3) {
            attacks.add(new WaterGun());
        }
        if (level >= 6) {
            attacks.add(new Confusion());
        }
        if (level >= 9) {
            attacks.add(new FurySwipes());
        }
    }
}
