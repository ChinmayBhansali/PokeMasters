package model.pokemon;

import model.Pokemon;
import model.attacks.Bite;
import model.attacks.RapidSpin;
import model.attacks.Tackle;
import model.attacks.WaterGun;

import java.util.ArrayList;
import java.util.Arrays;

public class Squirtle extends Pokemon {
    public Squirtle(int level) {
        super("Squirtle", level);
    }

    @Override
    public void setHP() {
        hp = 44 * level;
    }

    @Override
    public void setAttacks() {
        attacks = new ArrayList<>(Arrays.asList(new Tackle()));
        learnAttacks();
    }

    private void learnAttacks() {
        if (level >= 3) {
            attacks.add(new WaterGun());
        }
        if (level >= 9) {
            attacks.add(new RapidSpin());
        }
        if (level >= 12) {
            attacks.add(new Bite());
        }
    }
}
